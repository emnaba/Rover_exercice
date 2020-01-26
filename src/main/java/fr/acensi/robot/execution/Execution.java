package fr.acensi.robot.execution;

import fr.acensi.robot.utils.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Execution {
    private static final File RESOURCE_LOCATION = new File(ClassLoader
            .getSystemClassLoader()
            .getResource("")
            .getFile());

    private static String path = RESOURCE_LOCATION.getAbsolutePath();

    public static void main(String[] args) throws IOException {
    	//
        String inputPath = path + File.separator + "input.txt";
        String mappingPath = path + File.separator + "mapping.properties";
        List<String> lines = Services.getLinesFromFile(inputPath);
        Services.interpretLines(lines, mappingPath);
    }
}
