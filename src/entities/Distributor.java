package entities;

import input.DistributorInput;
import strategies.GreenStrategy;
import strategies.PriceStrategy;
import strategies.QuantityStrategy;
import strategies.Strategy;


import java.util.Collection;
import java.util.LinkedHashMap;

public final class Distributor {

  private int id;
  private int contractLength;
  private int budget;
  private int infrastructureCost;
  private int productionCost;
  private int lastCost;
  private final Strategy strategy;
  private final String strategyString;
  private int energyNeeded;
  private DistributorContract prodContract;
  private final LinkedHashMap<Integer, ConsumerContract> contracts =
          new LinkedHashMap<Integer, ConsumerContract>();

  public Distributor(final DistributorInput dInput) {
    this.id = dInput.getId();
    this.contractLength = dInput.getContractLength();
    this.budget = dInput.getInitialBudget();
    this.infrastructureCost = dInput.getInitialInfrastructureCost();
    this.productionCost = 0;
    this.lastCost = 0;
    this.strategy = setStrategy(dInput.getProducerStrategy());
    this.prodContract = null;
    this.energyNeeded = dInput.getEnergyNeededKW();
    this.strategyString = dInput.getProducerStrategy();
  }

  public int getId() {
    return id;
  }

  public int getContractLength() {
    return contractLength;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(final int budget) {
    this.budget = budget;
  }

  public int getInfrastructureCost() {
    return infrastructureCost;
  }

  public void setInfrastructureCost(final int infrastructureCost) {
    this.infrastructureCost = infrastructureCost;
  }

  public float getProductionCost() {
    return productionCost;
  }

  public void setProductionCost(final int productionCost) {
    this.productionCost = productionCost;
  }

  public DistributorContract getProdContract() {
    return prodContract;
  }

  public void setProdContract(DistributorContract prodContract) {
    this.prodContract = prodContract;
  }

  public int getEnergyNeeded() {
    return energyNeeded;
  }

  public void setEnergyNeeded(int energyNeeded) {
    this.energyNeeded = energyNeeded;
  }

  public String getStrategyString() {
    return strategyString;
  }

  /**
   * Sets the strategy based on input
   * @param str
   * @return
   */
  private Strategy setStrategy(String str) {
    switch (str) {
      case "GREEN":
        return GreenStrategy.getInstance();
      case "PRICE":
        return  PriceStrategy.getInstance();
      case "QUANTITY":
        return QuantityStrategy.getInstance();
      default:
        return null;
    }
  }

  /**
   *
   * @return
   */
  private void calculateProductionCost() {
    float sum = 0;
    for (Producer p: this.prodContract.getProducers()) {
      sum += p.getPriceKW() * p.getEnergyPerDistributor();
    }
    this.productionCost = (int) Math.round(Math.floor(sum / 10));
  }

  /**
   * Calculates the cost of a new contract
   * @return cost of the contract
   */
  public long calculateContractCost() {
    calculateProductionCost();
    if (this.contracts.size() == 0) {
      this.lastCost = Math.round(this.infrastructureCost + this.productionCost + this.getProfit());
      return this.lastCost;
    }
    this.lastCost = (int) Math.round(Math.floor(
            (double) this.infrastructureCost / this.contracts.size())
            + this.productionCost + this.getProfit());
    return lastCost;
  }

  public int getLastCost() {
    return lastCost;
  }

  /**
   * Calculaltes the profit
   * @return profit
   */
  private long getProfit() {
    return Math.round(Math.floor(0.2 * this.productionCost));
  }

  /**
   * Calculates total cost
   * @return monthly expenses
   */
  public int calculateCosts() {
    return this.infrastructureCost + this.productionCost * this.contracts.size();
  }

  public Collection<ConsumerContract> getContracts() {
    return this.contracts.values();
  }

  /**
   * Adds a contract
   * @param consumerContract
   */
  public void addContract(final ConsumerContract consumerContract) {
    this.contracts.put(consumerContract.getConsumerId(), consumerContract);
  }

  /**
   * Removes a contract if it exists
   * @param consumerContract
   */
  public void removeContract(final ConsumerContract consumerContract) {
    this.contracts.remove(consumerContract.getConsumerId());
  }

  /**
   * Removes the producer contract
   */
  public void removeProducerContract() {
    this.prodContract = null;
  }

  public Strategy getStrategy() {
    return this.strategy;
  }
}
