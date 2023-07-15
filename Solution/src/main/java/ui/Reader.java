package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Reader {

    static void readData(Search struct) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(struct.pathToStatesFile));

        int row = 0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.startsWith("#"))
                continue;

            if(row == 0) struct.startingState = line;
            else if(row == 1) struct.targetStates = Arrays.asList(line.split(" "));
            else {

                String[] fragmentedLine = line.split(":");

                if(fragmentedLine.length == 1) {
                    ArrayList<Node> mapNode = new ArrayList<>();
                    mapNode.add(new Node("empty", -1f));
                    struct.states.put(fragmentedLine[0].trim(), mapNode);
                } else {
                    String[] fragmentedStates = fragmentedLine[1].trim().split(" ");

                    int i = 0;
                    struct.states.put(fragmentedLine[0].trim(), new ArrayList<>());
                    do {
                        String[] stateInfo = fragmentedStates[i].split(",");
                        struct.states.get(fragmentedLine[0]).add(new Node(stateInfo[0].trim(), Float.parseFloat(stateInfo[1].trim())));
                        i++;
                    } while(i < fragmentedStates.length);

                    struct.states.put(fragmentedLine[0],
                            new ArrayList<>(struct.states.get(fragmentedLine[0])
                                    .stream().sorted(Comparator.comparing(Node::getName)).collect(Collectors.toList())));
                }
            }

            row++;
        }
    }

    static void readHeuristic(Search struct) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(struct.pathToHeuristicFile));

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.startsWith("#")) continue;

            String[] fragmentedLine = line.split(":");
            struct.heuristic.add(new Node(fragmentedLine[0].trim(), Float.parseFloat(fragmentedLine[1].trim())));
        }

        struct.heuristic.sort(Comparator.comparing((n) -> n.name));
    }
}
