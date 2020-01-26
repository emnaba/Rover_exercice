package fr.acensi.robot.utils;

import fr.acensi.robot.command.Command;
import fr.acensi.robot.command.CommandEnum;
import fr.acensi.robot.model.Plateau;
import fr.acensi.robot.model.Position;
import fr.acensi.robot.model.Robot;
import fr.acensi.robot.model.Rover;
import fr.acensi.robot.states.DirectionEnum;
import fr.acensi.robot.states.RoverDirectionState;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Services {

    /**
     * execute commands for a given robot from given text.
     * ensure that one robot is moving
     *
     * @param commandLine text that contains commands
     * @param robot       rover to control
     */
    private synchronized static void executeCommands(String commandLine, Robot robot) {
        Stream<Character> streamChars = toCharsByStream(commandLine);
        streamChars.forEach(character -> {
            Command command = CommandEnum.valueOf(character.toString()).getCommand();
            command.setRobot(robot);
            command.execute();
        });
    }

    /**
     * convert a given text to list of chars
     *
     * @param text including the command to execute
     * @return a stream of characters
     */
    public static Stream<Character> toCharsByStream(String text) {
        IntStream intStream = text.chars();
        Stream<Character> characterStream = intStream.mapToObj(c -> (char) c);
        return characterStream;
    }

    /**
     * read from a given path of mapping.properties file
     *
     * @param mappingPath path of the file
     * @return properties for each direction
     */
    public static Properties getMappingProperties(String mappingPath) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(mappingPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * interpret the lines of the input file, creates instances of rovers
     * and for each rover execute the corresponding command
     *
     * @param lines       list of lines
     * @param mappingPath path of input file
     * @return the output position of each rover
     */
    public static String interpretLines(List<String> lines, String mappingPath) {
        StringJoiner result = new StringJoiner("\n");
        if (lines != null && !lines.isEmpty()) {
            Plateau plateau = getPlateau(lines.get(0));
            IntStream.range(1, lines.size())
                    .filter(index -> index % 2 != 0)
                    .forEach(index -> {
                                String[] roverParams = lines.get(index).split(" ");
                                String commandLine = lines.get(index + 1);
                                Rover rover = createRover(roverParams, plateau);
                                executeCommands(commandLine, rover);
                                result.add(printRover(rover, getMappingProperties(mappingPath)));
                            }
                    );
        }
        return result.toString();
    }

    public static List<String> getLinesFromFile(String filePath) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream.collect(Collectors.toList());
        }
    }

    public static Plateau getPlateau(String firstLine) {
        if (firstLine != null && !firstLine.isEmpty()) {
            String[] xY = firstLine.split(" ");
            return new Plateau(Integer.valueOf(xY[0]), Integer.valueOf(xY[1]));
        }
        return null;
    }


    private static Rover createRover(String[] params, Plateau plateau) {
        int x = Integer.valueOf(params[0]);
        int y = Integer.valueOf(params[1]);
        Position position = new Position(x, y);
        RoverDirectionState directionState = DirectionEnum.valueOf(params[2]).getDirection();
        return new Rover(position, directionState, plateau);
    }


    public static String printRover(Rover rover, Properties properties) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(Integer.toString(rover.getPosition().getX()));
        stringJoiner.add(Integer.toString(rover.getPosition().getY()));
        String direction = rover.getDirection().getClass().getSimpleName();
        stringJoiner.add((String) properties.get(direction));
        return stringJoiner.toString();
    }
}
