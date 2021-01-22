package input;

public final class ProducerInput {

  private int id;
  private String energyType;
  private int maxDistributors;
  private float priceKW;
  private int energyPerDistributor;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEnergyType() {
    return energyType;
  }

  public void setEnergyType(String energyType) {
    this.energyType = energyType;
  }

  public int getMaxDistributors() {
    return maxDistributors;
  }

  public void setMaxDistributors(int maxDistributors) {
    this.maxDistributors = maxDistributors;
  }

  public float getPriceKW() {
    return priceKW;
  }

  public void setPriceKW(float priceKW) {
    this.priceKW = priceKW;
  }

  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }
}
