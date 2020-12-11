package input;

import java.util.ArrayList;

public class InitialData {
  private ArrayList<ConsumerInput> consumerInputs = new ArrayList<ConsumerInput>();
  private ArrayList<DistributorInput> distributorInputs = new ArrayList<DistributorInput>();

  public ArrayList<ConsumerInput> getConsumers() {
    return consumerInputs;
  }

  public void setConsumers(ArrayList<ConsumerInput> consumerInputs) {
    this.consumerInputs = consumerInputs;
  }

  public ArrayList<DistributorInput> getDistributors() {
    return distributorInputs;
  }

  public void setDistributors(ArrayList<DistributorInput> distributorInputs) {
    this.distributorInputs = distributorInputs;
  }
}
