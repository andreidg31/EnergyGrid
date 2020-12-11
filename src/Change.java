import input.CostChanges;

public final class Change {
  private int id;
  private int newInfrastructureCost;
  private int newProductionCost;

  public Change (final CostChanges change) {
    this.id = change.getId();
    this.newInfrastructureCost = change.getInfrastructureCost();
    this.newProductionCost = change.getProductionCost();
  }

  public int getId() {
    return id;
  }

  public int getNewInfrastructureCost() {
    return newInfrastructureCost;
  }

  public void setNewInfrastructureCost(int newInfrastructureCost) {
    this.newInfrastructureCost = newInfrastructureCost;
  }

  public int getNewProductionCost() {
    return newProductionCost;
  }

  public void setNewProductionCost(int newProductionCost) {
    this.newProductionCost = newProductionCost;
  }
}
