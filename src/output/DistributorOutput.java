package output;

import java.util.ArrayList;

public final class DistributorOutput {
  private int id;
  private int budget;
  private boolean isBankrupt;
  private ArrayList<ContractOutput> contracts;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(final int budget) {
    this.budget = budget;
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
