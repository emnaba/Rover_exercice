package fr.acensi.robot.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ServicesTest {
    private final static File RESOURCE_LOCATION = new File(ClassLoader
            .getSystemClassLoader()
            .getResource("")
            .getFile());
    private final static String PATH = RESOURCE_LOCATION.getAbsolutePath();

    final String MAPPING_FILE_NAME = "mapping.properties";
    final String INPUT_FILE_NAME = "input.txt";
    String inputPath;
    String mappingPath;

    @Before
    public void init() {
        inputPath = PATH + File.separator + INPUT_FILE_NAME;
        mappingPath = PATH + File.separator + MAPPING_FILE_NAME;
    }

    @Test
    public void testInputCommands() throws IOException {
        List<String> lines = Services.getLinesFromFile(inputPath);
        String result = Services.interpretLines(lines, mappingPath);
        Assert.assertEquals("1 3 N\n5 1 E", result);
    }

}
