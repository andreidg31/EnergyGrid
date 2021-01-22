package entities;

import input.DistributorChanges;

public final class DistributorChange {
  private int id;
  private final int newInfrastructureCost;

  public DistributorChange(final DistributorChanges dChange) {
    this.id = dChange.getId();
    this.newInfrastructureCost = dChange.getInfrastructureCost();
  }

  public int getId() {
    return id;
  }

  public int getNewInfrastructureCost() {
    return newInfrastructureCost;
  }


}
