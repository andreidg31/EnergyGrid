import input.ConsumerInput;


public final class Consumer {
  private final int id;
  private int budget;
  private final int monthlyIncome;
  private boolean isBankrupt = false;
  private Contract toPay = null;
  private Contract contract = null;

  public Consumer(final ConsumerInput cInput) {
    this.id = cInput.getId();
    this.budget = cInput.getInitialBudget();
    this.monthlyIncome = cInput.getMonthlyIncome();
  }

  public int getId() {
    return id;
  }

  public int getBudget() {
    return this.budget;
  }

  public void setBudget(final int budget) {
    this.budget = budget;
  }

  public int getMonthlyIncome() {
    return monthlyIncome;
  }

  public Contract getContract() {
    return this.contract;
  }

  public void setContract(final Contract contract) {
    this.contract = contract;
  }

  public Contract getToPay() {
    return this.toPay;
  }

  /**
   * The Consumer pays the contracts he has to,
   */
  public void payContracts() {
    int priceToPay = contract.getCost();
    if (this.toPay != null) {
      priceToPay += Math.floor(1.2 *  this.toPay.getCost());
    }
    contract.setContractMonths(contract.getContractMonths() - 1);
    if (this.budget < priceToPay) {
      if (this.toPay == null) {
        this.toPay = this.contract;
      } else {
        this.setBankrupt();
      }
    } else {
      this.budget -= priceToPay;
      this.contract.payDistributor();
      if (this.toPay != null) {
        this.toPay.payDistributor();
        this.toPay = null;
      }
    }
  }

  public boolean isBankrupt() {
    return isBankrupt;
  }

  /**
   * Makes the consumer bankrupt
   */
  public void setBankrupt() {
    this.isBankrupt = true;
  }
}
