package input;

import java.util.ArrayList;

public final class InitialData {
  private ArrayList<ConsumerInput> consumerInputs = new ArrayList<ConsumerInput>();
  private ArrayList<DistributorInput> distributorInputs = new ArrayList<DistributorInput>();
  private ArrayList<ProducerInput> producerInputs = new ArrayList<ProducerInput>();

  public ArrayList<ConsumerInput> getConsumers() {
    return this.consumerInputs;
  }

  public void setConsumers(final ArrayList<ConsumerInput> consumerInput) {
    this.consumerInputs = consumerInput;
  }

  public ArrayList<DistributorInput> getDistributors() {
    return this.distributorInputs;
  }

  public void setDistributors(final ArrayList<DistributorInput> distributorInput) {
    this.distributorInputs = distributorInput;
  }

  public ArrayList<ProducerInput> getProducers() {
    return producerInputs;
  }

  public void setProducers(ArrayList<ProducerInput> producerInput) {
    this.producerInputs = producerInput;
  }
}
