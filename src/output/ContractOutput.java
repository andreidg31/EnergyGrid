package output;

public class ContractOutput {
  private int consumerId;
  private int price;
  private int remainedContractMonths;
  public int getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(int consumerId) {
    this.consumerId = consumerId;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getRemainedContractMonths() {
    return remainedContractMonths;
  }

  public void setRemainedContractMonths(int remainedContractMonths) {
    this.remainedContractMonths = remainedContractMonths;
  }
}
