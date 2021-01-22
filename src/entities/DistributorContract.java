package entities;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public final class DistributorContract implements Observer {
  private Distributor distributor;
  private ArrayList<Producer> producers = new ArrayList<Producer>();
  private boolean isChanged = false;

  public DistributorContract(Distributor distributor) {
    this.distributor = distributor;
  }

  public Distributor getDistributor() {
    return distributor;
  }

  public void setDistributor(Distributor distributor) {
    this.distributor = distributor;
  }

  public ArrayList<Producer> getProducers() {
    return producers;
  }

  /**
   * Sets the producers of the contract
   * @param producers to be added
   */
  public void setProducers(ArrayList<Producer> producers) {
    this.producers = producers;
    for (Producer p : this.producers) {
      p.addContract(this);
    }
  }

  /**
   * Sets changed
   */
  public void setChanged() {
    this.isChanged = true;
  }

  /**
   * Checks if it's changed
   * @return true or false
   */
  public boolean hasChanged() {
    return this.isChanged;
  }

  /**
   * Removes all the contracts
   */
  public void removeContract() {
    for (Producer p: this.producers) {
      p.removeContract(this);
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    this.setChanged();
  }
}
