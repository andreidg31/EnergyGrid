public final class ContractFactory {

  /**
   * For coding style
   */
  private ContractFactory() {
  }

  /**
   * Creates a contract
   */
  public static Contract createContract(final Consumer c, final Distributor d) {
    return new Contract(c, d);
  }
}
