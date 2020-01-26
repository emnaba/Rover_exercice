package fr.acensi.robot.utils;


import fr.acensi.robot.command.Command;
import fr.acensi.robot.command.CommandEnum;
import fr.acensi.robot.model.Plateau;
import fr.acensi.robot.model.Position;
import fr.acensi.robot.model.Robot;
import fr.acensi.robot.model.Rover;
import fr.acensi.robot.states.DirectionEnum;
import fr.acensi.robot.states.RoverDirectionState;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Services {

    public static List<String> getLinesFromFile(String filePath) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream.collect(Collectors.toList());
        }
    }

    public static Plateau getPlateau(String firstLine) {
        if (firstLine != null && !firstLine.isEmpty()) {
            String[] xY = firstLine.split(" ");
            if (xY.length != 2) {
                //exception
            } else {
                return new Plateau(Integer.valueOf(xY[0]), Integer.valueOf(xY[1]));
            }
        }
        return null;
    }

    public static void interpretLines(List<String> lines, String mappingPath) {
        if (lines != null && !lines.isEmpty()) {
            Plateau plateau = getPlateau(lines.get(0));
            IntStream.range(1, lines.size())
                    .filter(index -> index % 2 != 0)
                    .forEach(index -> {
                                String[] roverParams = lines.get(index).split(" ");
                                String commandLine = lines.get(index + 1);
                                Rover rover = createRover(roverParams, plateau);
                                executeCommands(commandLine, rover);
                                try {
                                    printRover(rover, getMappingProperties(mappingPath));
                                } catch (FileNotFoundException e) {
                                    //TODO
                                }
                            }
                    );
        }


    }

    private static void executeCommands(String commandLine, Robot robot) {
        Stream<Character> streamChars = toCharsByStream(commandLine);
        streamChars.forEach(character -> {
            Command command = CommandEnum.valueOf(character.toString()).getCommand();
            command.setRobot(robot);
            command.execute();
        });
    }

    private static Rover createRover(String[] params, Plateau plateau) {
        int x = Integer.valueOf(params[0]);
        int y = Integer.valueOf(params[1]);
        Position position = new Position(x, y);
        RoverDirectionState directionState = DirectionEnum.valueOf(params[2]).getDirection();
        return new Rover(position, directionState, plateau);
    }

    /**
     * @param text
     * @return
     */
    public static Stream<Character> toCharsByStream(String text) {
        IntStream intStream = text.chars();
        Stream<Character> characterStream = intStream.mapToObj(c -> (char) c);
        return characterStream;
    }

    public static Properties getMappingProperties(String mappingPath) throws FileNotFoundException {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(mappingPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void printRover(Rover rover, Properties properties) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(Integer.toString(rover.getPosition().getX()));
        stringJoiner.add(Integer.toString(rover.getPosition().getY()));
        String direction = rover.getDirection().getClass().getSimpleName();
        stringJoiner.add((String) properties.get(direction));
        System.out.println(stringJoiner);
    }
}
