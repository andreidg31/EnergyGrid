
public final class Contract {
  private Consumer consumer;
  private Distributor distributor;
  private int cost;
  private int contractMonths;

  public Contract(Consumer c, Distributor d) {
    this.consumer = c;
    this.distributor = d;
    this.contractMonths = d.getContractLength();
    this.cost = (int)d.calculateContractCost();
  }

  public int getConsumerId() {
    return this.consumer.getId();
  }

  public int getCost() {
    return this.cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public int getContractMonths() {
    return contractMonths;
  }

  public void appendContract() {
    this.consumer.setContract(this);
    this.distributor.addContract(this);
  }

  public void expire() {
    this.consumer.setContract(null);
    this.distributor.removeContract(this);
  }

  public void setContractMonths(int contractMonths) {
    this.contractMonths = contractMonths;
  }
}
