package fr.acensi.robot.execution;

import fr.acensi.robot.utils.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Execution {

    public static void main(String[] args) throws IOException {
        final File RESOURCE_LOCATION;
        final String PATH;
        String inputPath;
        String mappingPath;
        final String MAPPING_FILE_NAME = "mapping.properties";

        try {
            RESOURCE_LOCATION = new File(ClassLoader
                    .getSystemClassLoader()
                    .getResource("")
                    .getFile());
            PATH = RESOURCE_LOCATION.getAbsolutePath();
            inputPath = PATH + File.separator + args[0];
            mappingPath = PATH + File.separator + MAPPING_FILE_NAME;
        } catch (Exception e) {
            inputPath = args[0];
            mappingPath = MAPPING_FILE_NAME;
        }
        List<String> lines = Services.getLinesFromFile(inputPath);
        System.out.println(Services.interpretLines(lines, mappingPath));
    }
}
