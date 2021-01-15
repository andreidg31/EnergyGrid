package entities;

public final class ConsDistContractFactory {

  /**
   * Consumer-Distributer contract factory
   */
  private ConsDistContractFactory() {
  }

  /**
   * Creates a contract
   */
  public static ConsumerContract createContract(final Consumer c, final Distributor d) {
    return new ConsumerContract(c, d);
  }
}
