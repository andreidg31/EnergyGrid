
import com.fasterxml.jackson.databind.ObjectMapper;
import input.InputData;
import output.OutputData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        String extra = "";
        ObjectMapper objectMapper = new ObjectMapper();
        InputData input = objectMapper.readValue(new File(extra + args[0]), InputData.class);

        EnergyGrid grid = new EnergyGrid(input);
        grid.simulate();

        OutputData output = grid.generateOutput();
        objectMapper.writeValue(new File(extra + args[1]), output);
    }
}
