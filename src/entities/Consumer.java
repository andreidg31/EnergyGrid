package entities;

import input.ConsumerInput;


public final class Consumer {
  private final int id;
  private int budget;
  private final int monthlyIncome;
  private boolean isBankrupt = false;
  private ConsumerContract toPay = null;
  private ConsumerContract consumerContract = null;

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

  public ConsumerContract getContract() {
    return this.consumerContract;
  }

  public void setContract(final ConsumerContract conContract) {
    this.consumerContract = conContract;
  }

  public ConsumerContract getToPay() {
    return this.toPay;
  }

  /**
   * The entities.Consumer pays the contracts he has to,
   */
  public void payContracts() {
    int priceToPay = consumerContract.getCost();
    if (this.toPay != null) {
      priceToPay += Math.floor(1.2 *  this.toPay.getCost());
    }
    consumerContract.setContractMonths(consumerContract.getContractMonths() - 1);
    if (this.budget < priceToPay) {
      if (this.toPay == null) {
        this.toPay = this.consumerContract;
      } else {
        this.setBankrupt();
      }
    } else {
      this.budget -= priceToPay;
      this.consumerContract.payDistributor();
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
