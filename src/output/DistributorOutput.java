package output;

import java.util.ArrayList;

public final class DistributorOutput {
  private int id;
  private int energyNeededKW;
  private int contractCost;
  private int budget;
  private String producerStrategy;
  private boolean isBankrupt;
  private ArrayList<ContractOutput> contracts;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public int getEnergyNeededKW() {
    return energyNeededKW;
  }

  public void setEnergyNeededKW(int energyNeededKW) {
    this.energyNeededKW = energyNeededKW;
  }

  public int getContractCost() {
    return contractCost;
  }

  public void setContractCost(int contractCost) {
    this.contractCost = contractCost;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(final int budget) {
    this.budget = budget;
  }

  public String getProducerStrategy() {
    return producerStrategy;
  }

  public void setProducerStrategy(String producerStrategy) {
    this.producerStrategy = producerStrategy;
  }

  public boolean getIsBankrupt() {
    return isBankrupt;
  }

  public void setIsBankrupt(final boolean bankrupt) {
    isBankrupt = bankrupt;
  }

  public ArrayList<ContractOutput> getContracts() {
    return contracts;
  }

  public void setContracts(final ArrayList<ContractOutput> contracts) {
    this.contracts = contracts;
  }
}
