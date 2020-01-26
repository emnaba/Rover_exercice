package fr.acensi.robot.execution;

import fr.acensi.robot.utils.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Execution {

    public static void main(String[] args) throws IOException {
        final File RESOURCE_LOCATION;
        final String path;
        String inputPath;
        String mappingPath;

        try {
            RESOURCE_LOCATION = new File(ClassLoader
                    .getSystemClassLoader()
                    .getResource("")
                    .getFile());
            path = RESOURCE_LOCATION.getAbsolutePath();
            inputPath = path + File.separator + args[0];
            mappingPath = path + File.separator + "mapping.properties";
        } catch (Exception e) {
            inputPath = args[0];
            mappingPath = "mapping.properties";
        }
        List<String> lines = Services.getLinesFromFile(inputPath);
        Services.interpretLines(lines, mappingPath);
    }
}
