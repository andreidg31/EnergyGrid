package strategies;


import entities.Producer;

import java.util.ArrayList;

public interface Strategy {
  /**
   * Sorts the producers based on the strategy chosen
   * @param producers to be sorted
   * @return sorted array
   */
  ArrayList<Producer> selectProducers(ArrayList<Producer> producers);
}
