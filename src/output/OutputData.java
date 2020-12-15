package output;

import java.util.ArrayList;

public final class OutputData {
  private ArrayList<ConsumerOutput> consumers = new ArrayList<ConsumerOutput>();
  private ArrayList<DistributorOutput> distributors = new ArrayList<DistributorOutput>();

  public ArrayList<ConsumerOutput> getConsumers() {
    return consumers;
  }

  public void setConsumers(final ArrayList<ConsumerOutput> consumers) {
    this.consumers = consumers;
  }

  public ArrayList<DistributorOutput> getDistributors() {
    return distributors;
  }

  public void setDistributors(final ArrayList<DistributorOutput> distributors) {
    this.distributors = distributors;
  }
}
