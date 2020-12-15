package output;

public final class ConsumerOutput {
  private int id;
  private boolean isBankrupt;
  private int budget;

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public boolean getIsBankrupt() {
    return isBankrupt;
  }

  public void setIsBankrupt(final boolean bankrupt) {
    isBankrupt = bankrupt;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(final int budget) {
    this.budget = budget;
  }
}
