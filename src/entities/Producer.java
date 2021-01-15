package entities;

import entities.EnergyType;
import input.ProducerInput;

import java.util.ArrayList;

public class Producer {
  private final int id;
  private EnergyType energyType;
  private int maxDistributors;
  private int distributors;
  private float priceKW;
  private int energyPerDistributor;
  private ArrayList<ArrayList<DistributorContract>> oldContracts =
          new ArrayList<ArrayList<DistributorContract>>();
  private ArrayList<DistributorContract> currentContracts = new ArrayList<DistributorContract>();

  public Producer(ProducerInput input) {
    this.id = input.getId();
    this.energyType = Utils.stringToEnergy(input.getEnergyType());
    this.maxDistributors = input.getMaxDistributors();
    this.distributors = this.maxDistributors;
    this.priceKW = input.getPriceKW();
    this.energyPerDistributor = input.getEnergyPerDistributor();
  }

  public boolean isGreen() {
    return this.energyType.isRenewable();
  }

  public int getId() {
    return id;
  }

  public float getPriceKW() {
    return priceKW;
  }

  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public int getDistributors() {
    return this.distributors;
  }

  public void setDistributors(int distributors) {
    this.distributors = distributors;
  }

  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
    notifyObservers();
  }

  public void removeContract(DistributorContract contract) {
    this.currentContracts.remove(contract);
  }

  public void addContract(DistributorContract contract) {
    this.currentContracts.add(contract);
  }

  private void notifyObservers() {
    for (DistributorContract dc: this.currentContracts) {
      dc.setChanged();
    }
  }
  public void moveContracts() {
    this.oldContracts.add(this.currentContracts);
  }

  @Override
  public String toString() {
    return "Producer{" +
            "id=" + id +
            ", energyType=" + energyType +
            ", maxDistributors=" + maxDistributors +
            ", distributors=" + distributors +
            ", priceKW=" + priceKW +
            ", energyPerDist" + energyPerDistributor +
            "}\n";
  }
}
