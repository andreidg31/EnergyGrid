package entities;

import java.util.ArrayList;

public class DistributorContract {
  private Distributor distributor;
  private ArrayList<Producer> producers = new ArrayList<Producer>();
  private boolean isChanged = false;

  public DistributorContract() {
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

  public void setProducers(ArrayList<Producer> producers) {
    this.producers = producers;
  }

  public void setChanged() {
    this.isChanged = true;
  }

  public boolean hasChanged() {
    return this.isChanged;
  }

  public void removeContract() {
    for (Producer p: this.producers) {
      p.removeContract(this);
    }
  }
}
