import input.InputData;
import com.fasterxml.jackson.databind.ObjectMapper;
import output.OutputData;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        Path cPath = Paths.get("");
        String path = Paths.get(cPath.toAbsolutePath().toString(),"src", args[0]).toAbsolutePath().toString();
        System.out.println(path);
        ObjectMapper objectMapper = new ObjectMapper();
        InputData input = objectMapper.readValue(new File(path), InputData.class);
        System.out.println(input.getNumberOfTurns());

        /*EnergyGrid.createInstance(input);
        EnergyGrid.getInstance().simulate();

        OutputData output = EnergyGrid.getInstance().generateOutput();
        objectMapper.writeValue(new File(args[1]), output);

         */
    }
}
