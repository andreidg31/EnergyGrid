package strategies;

import entities.Producer;
import entities.Utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class QuantityStrategy implements Strategy {

  private static final QuantityStrategy instance = new QuantityStrategy();

  /**
   * For conding style;
   */
  private QuantityStrategy(){

  }

  public static QuantityStrategy getInstance() {
    return instance;
  }

  @Override
  public ArrayList<Producer> selectProducers(ArrayList<Producer> producers) {
    return new ArrayList<Producer>(producers
            .stream()
            .sorted(Utils.sortProducersQuantity())
            .collect(Collectors.toList()));
  }

}
