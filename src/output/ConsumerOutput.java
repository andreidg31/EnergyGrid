package output;

public class ConsumerOutput {
  private int id;
  private boolean isBankrupt;
  private int budget;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean getIsBankrupt() {
    return isBankrupt;
  }

  public void setIsBankrupt(boolean bankrupt) {
    isBankrupt = bankrupt;
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }
}
