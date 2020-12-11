import input.ConsumerInput;

import java.util.ArrayList;

public final class Consumer {
  private int id;
  private int budget;
  private int monthlyIncome;
  private boolean isBankrupt = false;
  private int toPay = 0;
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
    return budget;
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

  public void payContracts() {
    int priceToPay = (6 * this.toPay) / 5 + contract.getCost();
    contract.setContractMonths(contract.getContractMonths() - 1);
    if (this.budget < priceToPay) {
      if (this.toPay == 0) {
        this.toPay = priceToPay;
      }
      else {
        this.setBankrupt();
      }
    }
    else {
      this.budget -= priceToPay;
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
            ", monthlyIncome=" + monthlyIncome +
            ", isBankrupt=" + isBankrupt +
            "}\n";
  }
}
