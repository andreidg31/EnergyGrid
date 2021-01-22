package entities;

public final class ConsumerContract {
  private Consumer consumer;
  private Distributor distributor;
  private int cost;
  private int contractMonths;

  public ConsumerContract(final Consumer c, final Distributor d) {
    this.consumer = c;
    this.distributor = d;
    this.contractMonths = d.getContractLength();
    this.cost = (int) d.calculateContractCost();
  }

  public int getConsumerId() {
    return this.consumer.getId();
  }

  public int getDistributorId() {
    return this.distributor.getId();
  }
  public int getCost() {
    return this.cost;
  }

  public int getContractMonths() {
    return contractMonths;
  }

  /**
   * Appends the contract to the consumer and the distributor
   */
  public void appendContract() {
    this.consumer.setContract(this);
    this.distributor.addContract(this);
  }

  /**
   * Removes the contract from the consumer and distributor
   */
  public void expire() {
    this.consumer.setContract(null);
    this.distributor.removeContract(this);
  }

  /**
   * Pays the distributor the amount decided
   */
  public void payDistributor() {
    this.distributor.setBudget(this.distributor.getBudget() + this.cost);
  }

  public void setContractMonths(final int contractMonths) {
    this.contractMonths = contractMonths;
  }
}
