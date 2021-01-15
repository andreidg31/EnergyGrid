package strategies;


import entities.Producer;

import java.util.ArrayList;

public interface Strategy {
  public ArrayList<Producer> selectProducers(final ArrayList<Producer> producers);
}
