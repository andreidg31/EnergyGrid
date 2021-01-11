package input;

import java.util.ArrayList;

public final class MonthlyUpdates {
  private ArrayList<ConsumerInput> newConsumerInputs = new ArrayList<ConsumerInput>();
  private ArrayList<DistributorChanges> distributorChanges = new ArrayList<DistributorChanges>();
  private ArrayList<ProducerChanges> producerChanges = new ArrayList<ProducerChanges>();

  public ArrayList<ConsumerInput> getNewConsumers() {
    return newConsumerInputs;
  }

  public void setNewConsumers(final ArrayList<ConsumerInput> newConsumerInputs) {
    this.newConsumerInputs = newConsumerInputs;
  }

  public ArrayList<DistributorChanges> getDistributorChanges() {
    return distributorChanges;
  }

  public void setDistributorChanges(final ArrayList<DistributorChanges> distributorChanges) {
    this.distributorChanges = distributorChanges;
  }

  public ArrayList<ProducerChanges> getProducerChanges() {
    return producerChanges;
  }

  public void setProducerChanges(ArrayList<ProducerChanges> producerChanges) {
    this.producerChanges = producerChanges;
  }
}
