package entities;

import input.ProducerInput;

import java.util.ArrayList;
import java.util.Observable;

public final class Producer extends Observable {
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

  public EnergyType getEnergyType() {
    return energyType;
  }

  public int getMaxDistributors() {
    return maxDistributors;
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

  public ArrayList<DistributorContract> getCurrentContracts() {
    return currentContracts;
  }

  public ArrayList<ArrayList<DistributorContract>> getOldContracts() {
    return oldContracts;
  }

  public void setDistributors(int distributors) {
    this.distributors = distributors;
  }

  /**
   * Sets the energy per distributor and notifies observers
   * @param energyPerDistributor value to be replaced
   */
  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
    setChanged();
    notifyObservers();
  }

  /**
   * Removes a contract from the producer
   * @param contract to be removed
   */
  public void removeContract(DistributorContract contract) {
    this.distributors++;
    this.currentContracts.remove(contract);
    this.deleteObserver(contract);
  }

  /**
   * Adds a contraact to the producer
   * @param contract to be added
   */
  public void addContract(DistributorContract contract) {
    this.distributors--;
    this.currentContracts.add(contract);
    this.addObserver(contract);
  }

  /**
   * Moves the contracts to the old ones
   * @param contracts to be moved
   */
  public void moveContracts(ArrayList<DistributorContract> contracts) {
    this.oldContracts.add(contracts);
  }
}
