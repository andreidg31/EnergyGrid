import input.CostChanges;

public final class Change {
  private int id;
  private final int newInfrastructureCost;
  private final int newProductionCost;

  public Change(final CostChanges change) {
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

  public int getNewProductionCost() {
    return newProductionCost;
  }

}
