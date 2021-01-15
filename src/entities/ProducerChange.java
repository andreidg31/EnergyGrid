package entities;

import input.ProducerChanges;

public class ProducerChange {
  private final int id;
  private final int newEnergy;

  public ProducerChange(ProducerChanges pChange) {
    this.id = pChange.getId();
    this.newEnergy = pChange.getEnergyPerDistributor();
  }

  public int getId() {
    return id;
  }

  public int getNewEnergy() {
    return newEnergy;
  }
}
