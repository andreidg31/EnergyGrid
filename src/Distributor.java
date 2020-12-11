import input.DistributorInput;


import java.util.Collection;
import java.util.LinkedHashMap;

public final class Distributor {
  private final int id;
  private int contractLength;
  private int budget;
  private int infrastructureCost;
  private int productionCost;
  private final LinkedHashMap<Integer, Contract> contracts =
          new LinkedHashMap<Integer, Contract>();

  public Distributor(DistributorInput dInput) {
    this.id = dInput.getId();
    this.contractLength = dInput.getContractLength();
    this.budget = dInput.getInitialBudget();
    this.infrastructureCost = dInput.getInitialInfrastructureCost();
    this.productionCost = dInput.getInitialProductionCost();
  }

  public int getId() {
    return id;
  }

  public int getContractLength() {
    return contractLength;
  }

  public void setContractLength(int contractLength) {
    this.contractLength = contractLength;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public int getInfrastructureCost() {
    return infrastructureCost;
  }

  public void setInfrastructureCost(int infrastructureCost) {
    this.infrastructureCost = infrastructureCost;
  }

  public int getProductionCost() {
    return productionCost;
  }

  public void setProductionCost(int productionCost) {
    this.productionCost = productionCost;
  }

  public long calculateContractCost() {
    if (this.contracts.size() == 0) {
      return this.infrastructureCost + this.productionCost + this.getProfit();
    }
    return Math.round(Math.floor((double)this.infrastructureCost / this.contracts.size()) +
            this.productionCost + this.getProfit());
  }

  private long getProfit() {
    return Math.round(Math.floor(0.2 * this.productionCost));
  }

  public int calculateCosts() {
    return this.infrastructureCost + this.productionCost * this.contracts.size();
  }

  public Collection<Contract> getContracts() {
    return this.contracts.values();
  }

  public void addContract(Contract contract) {
    this.contracts.put(contract.getConsumerId(), contract);
  }

  public void removeContract(Contract contract) {
    this.contracts.remove(contract.getConsumerId());
  }

  @Override
  public String toString() {
    return "Distributor{" +
            "id=" + id +
            ", contractLength=" + contractLength +
            ", budget=" + budget +
            ", infrastructureCost=" + infrastructureCost +
            ", productionCost=" + productionCost +
            "}\n";
  }
}
