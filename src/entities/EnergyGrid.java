package entities;

import input.ConsumerInput;
import input.DistributorInput;
import input.InputData;
import input.MonthlyUpdates;
import input.ProducerInput;
import output.ConsumerOutput;
import output.ContractOutput;
import output.DistributorOutput;
import output.MonthlyStatsOutput;
import output.OutputData;
import output.ProducerOutput;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public final class EnergyGrid {

  private int numberOfTurns;
  private final LinkedHashMap<Integer, Consumer> consumers =
          new LinkedHashMap<Integer, Consumer>();
  private final LinkedHashMap<Integer, Distributor> distributors =
          new LinkedHashMap<Integer, Distributor>();
  private  final LinkedHashMap<Integer, Producer> producers =
          new LinkedHashMap<Integer, Producer>();
  private final ArrayList<ArrayList<Consumer>> newConsumers =
          new ArrayList<ArrayList<Consumer>>();
  private final ArrayList<ArrayList<DistributorChange>> dChanges =
          new ArrayList<ArrayList<DistributorChange>>();
  private final ArrayList<ArrayList<ProducerChange>> pChanges =
          new ArrayList<ArrayList<ProducerChange>>();
  private final ArrayList<Distributor> bankruptDist = new ArrayList<Distributor>();

  public EnergyGrid(final InputData input) {

    this.numberOfTurns = input.getNumberOfTurns();
    // Add initial consumers
    for (ConsumerInput consumer : input.getInitialData().getConsumers()) {
      this.consumers.put(consumer.getId(), new Consumer(consumer));
    }
    // Add initial distributors
    for (DistributorInput distributor : input.getInitialData().getDistributors()) {
      this.distributors.put(distributor.getId(), new Distributor(distributor));
    }
    // Add initial producers
    for (ProducerInput producer : input.getInitialData().getProducers()) {
      this.producers.put(producer.getId(), new Producer(producer));
    }
    // Add new consumers and changes
    for (MonthlyUpdates update : input.getMonthlyUpdates()) {
      ArrayList<Consumer> newC = new ArrayList<Consumer>();
      update.getNewConsumers().forEach(consumerInput -> newC.add(new Consumer(consumerInput)));

      ArrayList<DistributorChange> chg = new ArrayList<DistributorChange>();
      update.getDistributorChanges()
              .forEach(costChanges -> chg.add(new DistributorChange(costChanges)));

      ArrayList<ProducerChange> pChg = new ArrayList<ProducerChange>();
      update.getProducerChanges()
              .forEach(producerChanges -> pChg.add(new ProducerChange(producerChanges)));

      this.newConsumers.add(newC);
      this.dChanges.add(chg);
      this.pChanges.add(pChg);
    }
  }

  /**
   * Simulates the game
   */
  public void simulate() {

    addSalaries();
    assignProducers();
    generateContracts();
    payContracts();
    payCosts();
    clearBankrupt();
    for (int counter = 0; counter < this.numberOfTurns; counter++) {
      // System.out.println("MONTH: " + counter);
      if (this.distributors.isEmpty()) {
        break;
      }
      updateMonthly(counter);
      addSalaries();
      assignProducers();
      generateContracts();
      payContracts();
      payCosts();
      clearBankrupt();
      updateProducers(counter);
      moveToOld();
    }
    assignProducers();
    moveToOld();
  }

  /**
   * Populates the old contracts
   */
  private void moveToOld() {
    for (Producer p: this.producers.values()) {

      p.moveContracts(new ArrayList<DistributorContract>(p.getCurrentContracts()));
    }
  }
  /**
   * Assigns producers to the distributors
   */
  private void assignProducers() {
    ArrayList<Producer> sortedProducers = new ArrayList<Producer>(this.producers.values());

    for (Distributor dist: this.distributors.values()) {
      if (dist.getProdContract() == null) {
        createProdContract(dist, sortedProducers);
        continue;
      }
      if (dist.getProdContract() != null && dist.getProdContract().hasChanged()) {
        dist.getProdContract().removeContract();
        dist.removeProducerContract();
        createProdContract(dist, sortedProducers);
      }
    }
  }

  private void createProdContract(Distributor dist, ArrayList<Producer> prod) {
    int energyProvided = 0;
    DistributorContract contr = new DistributorContract(dist);
    prod = (ArrayList<Producer>) dist.getStrategy().selectProducers(prod)
            .stream().filter(p -> p.getDistributors() > 0).collect(Collectors.toList());
    ArrayList<Producer> producersRequired = new ArrayList<Producer>();
    energyProvided = 0;
    for (Producer p: prod) {
      if (p.getDistributors() == 0) {
        continue;
      }
      producersRequired.add(p);
      energyProvided += p.getEnergyPerDistributor();
      if (energyProvided > dist.getEnergyNeeded()) {
        break;
      }
    }
    dist.setProdContract(contr);
    contr.setProducers(producersRequired);
  }
  /**
   * Generates the contracts for the users
   */
  private void generateContracts() {
    ArrayList<Distributor> sortedDist = new ArrayList<Distributor>(this.distributors.values());
    sortedDist.sort(Utils.sortProdCost());
    ArrayList<ConsumerContract> contractsToSign = new ArrayList<ConsumerContract>();
    for (Consumer consumer: this.consumers.values()) {
      if (!consumer.isBankrupt()) {
        ConsumerContract consumerContract =
                ConsDistContractFactory.createContract(consumer, sortedDist.get(0));
        contractsToSign.add(consumerContract);
      }
    }
    clearFinishedContracts();
    for (ConsumerContract consumerContract : contractsToSign) {
      Consumer consumer = this.consumers.get(consumerContract.getConsumerId());

      if (consumer.getContract() == null) {
        consumerContract.appendContract();
      }
    }
  }

  /**
   * Removes the finished contracts
   */
  private void clearFinishedContracts() {
    for (Consumer consumer: this.consumers.values()) {
      ConsumerContract consumerContract = consumer.getContract();
      if (consumerContract != null && consumerContract.getContractMonths() == 0) {
          consumerContract.expire();
      }
    }
  }

  /**
   * Clears bankrupt consumers contracts
   */
  private void clearBankrupt() {
    for (Consumer consumer: this.consumers.values()) {
      ConsumerContract consumerContract = consumer.getContract();
      if (consumerContract != null && consumer.isBankrupt()) {
        consumerContract.expire();
      }
    }
  }

  /**
   * Adds new consumers, and updates distributers costs
   * @param month of the game
   */
  private void updateMonthly(final int month) {
    // Add new consumers
    for (Consumer consumer: this.newConsumers.get(month)) {
      this.consumers.put(consumer.getId(), consumer);
    }
    // Distributor cost change
    for (DistributorChange distributorChange : this.dChanges.get(month)) {
      Distributor d = this.distributors.get(distributorChange.getId());
      if (d != null) {
        d.setInfrastructureCost(distributorChange.getNewInfrastructureCost());
      }
    }
  }

  private  void updateProducers(int month) {
    // Producer energy change
    for (ProducerChange producerChange : this.pChanges.get(month)) {
      Producer p = this.producers.get(producerChange.getId());
      if (p != null) {
        p.setEnergyPerDistributor(producerChange.getNewEnergy());
      }
    }
  }
  /**
   * Adds the salaries of the consumers
   */
  private void addSalaries() {
    for (Consumer consumer: this.consumers.values()) {
      if (!consumer.isBankrupt()) {
        consumer.setBudget(consumer.getBudget() + consumer.getMonthlyIncome());
      }
    }
  }

  private void payContracts() {
    for (Consumer consumer: this.consumers.values()) {
      // Exclude bankrupt consumers
      if (consumer.isBankrupt()) {
        continue;
      }
      consumer.payContracts();
    }
  }

  private void payCosts() {
    ArrayList<Distributor> toRemove = new ArrayList<Distributor>();
    for (Distributor d: this.distributors.values()) {
      d.setBudget(d.getBudget() - d.calculateCosts());
      if (d.getBudget() < 0) {
        this.bankruptDist.add(d);
        toRemove.add(d);
      }
    }
    this.distributors.values().removeAll(toRemove);
  }

  /**
   * Generates the output data for checker
   * @return
   */
  public OutputData generateOutput() {
    OutputData output = new OutputData();
    // Consumer output
    for (Consumer consumer: this.consumers.values()) {
      ConsumerOutput consumerOutput = new ConsumerOutput();
      consumerOutput.setId(consumer.getId());
      consumerOutput.setBudget(consumer.getBudget());
      consumerOutput.setIsBankrupt(consumer.isBankrupt());
      output.getConsumers().add(consumerOutput);
    }
    // Distributor and bankrupt distributors output
    ArrayList<Distributor> allDist = new ArrayList<Distributor>(this.distributors.values());
    allDist.addAll(this.bankruptDist);
    allDist.sort(Utils.sortById());
    for (Distributor distributor: allDist) {
      DistributorOutput distributorOutput = new DistributorOutput();
      distributorOutput.setId(distributor.getId());
      distributorOutput.setEnergyNeededKW(distributor.getEnergyNeeded());
      distributorOutput.setContractCost(distributor.getLastCost());
      distributorOutput.setProducerStrategy(distributor.getStrategyString());
      distributorOutput.setBudget(distributor.getBudget());
      if (distributor.getBudget() < 0) {
        distributorOutput.setIsBankrupt(true);
        distributorOutput.setContracts(new ArrayList<>());
      } else {
        distributorOutput.setIsBankrupt(false);
        distributorOutput.setContracts(generateOutputContracts(distributor));
      }
      output.getDistributors().add(distributorOutput);
    }
    // Producer output
    for (Producer p : this.producers.values()) {
      ProducerOutput producerOutput = new ProducerOutput();
      producerOutput.setId(p.getId());
      producerOutput.setEnergyType(p.getEnergyType().getLabel());
      producerOutput.setMaxDistributors(p.getMaxDistributors());
      producerOutput.setEnergyPerDistributor(p.getEnergyPerDistributor());
      producerOutput.setPriceKW(p.getPriceKW());

      ArrayList<MonthlyStatsOutput> monthly = new ArrayList<MonthlyStatsOutput>();
      for (int i = 1; i <= this.numberOfTurns; i++) {
        MonthlyStatsOutput el = new MonthlyStatsOutput();
        el.setMonth(i);
//        ArrayList<Integer> ids = new ArrayList<Integer>();
//        p.getOldContracts().get(i).forEach(contract -> {
//          ids.add(contract.getDistributor().getId());
//        });
//        el.setDistributorsIds(ids);
        el.setDistributorsIds(p.getOldContracts().get(i)
                .stream()
                .map(contract -> contract.getDistributor().getId())
                .sorted()
                .collect(Collectors.toList()));
        monthly.add(el);
      }
      producerOutput.setMonthlyStats(monthly);
      output.getEnergyProducers().add(producerOutput);
    }
    return output;
  }

  /**
   * Generates a list of contracts for output
   * @param distributor for output
   * @return output contracts
   */
  private ArrayList<ContractOutput> generateOutputContracts(final Distributor distributor) {
    ArrayList<ContractOutput> contracts = new ArrayList<ContractOutput>();
    for (ConsumerContract consumerContract : distributor.getContracts()) {
      ContractOutput out = new ContractOutput();
      out.setConsumerId(consumerContract.getConsumerId());
      out.setRemainedContractMonths(consumerContract.getContractMonths());
      out.setPrice(consumerContract.getCost());
      contracts.add(out);
    }
    return contracts;
  }
}
