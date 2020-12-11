import input.ConsumerInput;

import java.util.ArrayList;

public final class Consumer {
  private int id;
  private int budget;
  private int monthlyIncome;
  private boolean isBankrupt = false;
  private Contract toPay = null;
  private Contract contract = null;

  public Consumer(ConsumerInput cInput) {
    this.id = cInput.getId();
    this.budget = cInput.getInitialBudget();
    this.monthlyIncome = cInput.getMonthlyIncome();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBudget() {
    return this.budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public int getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(int monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public Contract getContract() {return this.contract;}

  public void setContract(Contract contract) {this.contract = contract;}

  public Contract getToPay() {return this.toPay;}

  public void payContracts() {
    int priceToPay = contract.getCost();
    if (this.toPay != null) {
      priceToPay += (6 * this.toPay.getCost()) / 5;
    }
    contract.setContractMonths(contract.getContractMonths() - 1);
    if (this.budget < priceToPay) {
      if (this.toPay == null) {
        this.toPay = this.contract;
      }
      else {
        this.setBankrupt();
      }
    }
    else {
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

  public void setBankrupt() {
    this.isBankrupt = true;
  }

  @Override
  public String toString() {
    return "Consumer{" +
            "id=" + id +
            ", budget=" + budget +
            ", isBankrupt=" + isBankrupt +
            ", toPay=" + toPay +
            ", contract=" + contract +
            "}\n";
  }
}
