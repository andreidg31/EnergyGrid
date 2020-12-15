package input;

import java.util.ArrayList;

public final class MonthlyUpdates {
  private ArrayList<ConsumerInput> newConsumerInputs = new ArrayList<ConsumerInput>();
  private ArrayList<CostChanges> costsChanges = new ArrayList<CostChanges>();

  public ArrayList<ConsumerInput> getNewConsumers() {
    return newConsumerInputs;
  }

  public void setNewConsumers(final ArrayList<ConsumerInput> newConsumerInputs) {
    this.newConsumerInputs = newConsumerInputs;
  }

  public ArrayList<CostChanges> getCostsChanges() {
    return costsChanges;
  }

  public void setCostsChanges(final ArrayList<CostChanges> costsChanges) {
    this.costsChanges = costsChanges;
  }
}
