package input;

import java.util.ArrayList;

public final class InitialData {
  private ArrayList<ConsumerInput> consumerInputs = new ArrayList<ConsumerInput>();
  private ArrayList<DistributorInput> distributorInputs = new ArrayList<DistributorInput>();

  public ArrayList<ConsumerInput> getConsumers() {
    return this.consumerInputs;
  }

  public void setConsumers(final ArrayList<ConsumerInput> consumerInputs) {
    this.consumerInputs = consumerInputs;
  }

  public ArrayList<DistributorInput> getDistributors() {
    return this.distributorInputs;
  }

  public void setDistributors(final ArrayList<DistributorInput> distributorInputs) {
    this.distributorInputs = distributorInputs;
  }
}
