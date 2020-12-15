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

  public Distributor(final DistributorInput dInput) {
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

  public int getProductionCost() {
    return productionCost;
  }

  public void setProductionCost(final int productionCost) {
    this.productionCost = productionCost;
  }

  /**
   * Calculates the cost of a new contract
   * @return cost of the contract
   */
  public long calculateContractCost() {
    if (this.contracts.size() == 0) {
      return this.infrastructureCost + this.productionCost + this.getProfit();
    }
    return Math.round(Math.floor((double) this.infrastructureCost / this.contracts.size())
            + this.productionCost + this.getProfit());
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

  public Collection<Contract> getContracts() {
    return this.contracts.values();
  }

  /**
   * Adds a contract
   * @param contract
   */
  public void addContract(final Contract contract) {
    this.contracts.put(contract.getConsumerId(), contract);
  }

  /**
   * Removes a contract if it exists
   * @param contract
   */
  public void removeContract(final Contract contract) {
    this.contracts.remove(contract.getConsumerId());
  }
}
