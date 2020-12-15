
import com.fasterxml.jackson.databind.ObjectMapper;
import input.InputData;
import output.OutputData;

import java.io.File;

public final class Main {

    /**
     * For coding style
     */
    private Main() {

    }

    /**
     * Entry point for the aplication
     * @param args args[0] -> input file, args[1] -> output file
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        InputData input = objectMapper.readValue(new File(args[0]), InputData.class);

        EnergyGrid.createInstance(input);
        EnergyGrid.getInstance().simulate();

        OutputData output = EnergyGrid.getInstance().generateOutput();
        objectMapper.writeValue(new File(args[1]), output);
    }
}
