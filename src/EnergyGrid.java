import input.ConsumerInput;
import input.DistributorInput;
import input.MonthlyUpdates;
import input.InputData;
import output.ConsumerOutput;
import output.ContractOutput;
import output.DistributorOutput;
import output.OutputData;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class EnergyGrid {

  private final int numberOfTurns;
  private final LinkedHashMap<Integer, Consumer> consumers =
          new LinkedHashMap<Integer, Consumer>();;
  private final LinkedHashMap<Integer, Distributor> distributors =
          new LinkedHashMap<Integer, Distributor>();;
  private final ArrayList<ArrayList<Consumer>> newConsumers =
          new ArrayList<ArrayList<Consumer>>();
  private final ArrayList<ArrayList<Change>> changes = new ArrayList<ArrayList<Change>>();
  private final ArrayList<Distributor> bankruptDist = new ArrayList<Distributor>();

  public EnergyGrid(InputData input) {
    this.numberOfTurns = input.getNumberOfTurns();
    // Add initial consumers
    for (ConsumerInput consumer : input.getInitialData().getConsumers()) {
      this.consumers.put(consumer.getId(), new Consumer(consumer));
    }
    // Add initial distributors
    for (DistributorInput distributor : input.getInitialData().getDistributors()) {
      this.distributors.put(distributor.getId(), new Distributor(distributor));
    }
    // Add new consumers and changes
    for (MonthlyUpdates update : input.getMonthlyUpdates()) {
      ArrayList<Consumer> newC = new ArrayList<Consumer>();
      update.getNewConsumers().forEach(consumerInput -> newC.add(new Consumer(consumerInput)));

      ArrayList<Change> chg = new ArrayList<Change>();
      update.getCostsChanges().forEach(costChanges -> chg.add(new Change(costChanges)));

      this.newConsumers.add(newC);
      this.changes.add(chg);
    }
  }

  public void simulate() {

    for (int counter = 0; counter < this.numberOfTurns; counter++) {
      if (this.distributors.isEmpty()) {
        break;
      }
    }
  }

  public void generateContracts() {
    ArrayList<Distributor> sortedDist = new ArrayList<Distributor>(this.distributors.values());
    sortedDist.sort(Utils.sortProdCost());
    ArrayList<Contract> contractsToSign = new ArrayList<Contract>();
    for (Consumer c: this.consumers.values()) {
      if (!c.isBankrupt() && !(c.getContract() == null)) {
        Contract contract = new Contract(c, sortedDist.get(0));
        contractsToSign.add(contract);
      }
    }

    for (Contract contract: contractsToSign) {
      contract.appendContract();
    }
  }

  public OutputData generateOutput() {
    OutputData output = new OutputData();
    for (Consumer consumer: this.consumers.values()) {
      ConsumerOutput consumerOutput = new ConsumerOutput();
      consumerOutput.setId(consumer.getId());
      consumerOutput.setBudget(consumer.getBudget());
      consumerOutput.setIsBankrupt(consumer.isBankrupt());
      output.getConsumers().add(consumerOutput);
    }

    ArrayList<Distributor> allDist = new ArrayList<Distributor>(this.distributors.values());
    allDist.addAll(this.bankruptDist);
    allDist.sort(Utils.sortById());
    for (Distributor distributor: allDist) {
      DistributorOutput distributorOutput = new DistributorOutput();
      distributorOutput.setId(distributor.getId());
      distributorOutput.setBudget(distributor.getBudget());
      if (distributor.getBudget() < 0) {
        distributorOutput.setIsBankrupt(true);
        distributorOutput.setContracts(new ArrayList<>());
      }
      else {
        distributorOutput.setIsBankrupt(false);
        //distributorOutput.setContracts(generateOutputContracts(distributor));
      }
      output.getDistributors().add(distributorOutput);
    }
    return output;
  }
}
