import entities.EnergyGrid;
import input.InputData;
import com.fasterxml.jackson.databind.ObjectMapper;
import output.OutputData;

import java.io.File;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        InputData input = objectMapper.readValue(new File(args[0]), InputData.class);

        EnergyGrid game = new EnergyGrid(input);
        game.simulate();

        OutputData output = game.generateOutput();
        objectMapper.writeValue(new File(args[1]), output);

    }
}
