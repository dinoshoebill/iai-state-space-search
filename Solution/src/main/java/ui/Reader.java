package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public final class Reader {

    private Reader() {
    }

    static void readData(Search struct) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(struct.pathToStatesFile))) {
            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#")) {
                    continue;
                }

                if (row == 0) {
                    struct.startingState = line;
                } else if (row == 1) {
                    struct.targetStates = new HashSet<>(Arrays.asList(line.split(" ")));
                } else {
                    parseStateLine(struct, line);
                }
                row++;
            }
        }
    }

    private static void parseStateLine(Search struct, String line) {
        String[] parts = line.split(":");
        String stateName = parts[0].trim();

        if (parts.length == 1) {
            ArrayList<Node> emptyAdjacency = new ArrayList<>();
            emptyAdjacency.add(new Node("empty", -1f));
            struct.states.put(stateName, emptyAdjacency);
            return;
        }

        String[] adjacentEntries = parts[1].trim().split(" ");
        List<Node> adjacent = new ArrayList<>(adjacentEntries.length);
        for (String entry : adjacentEntries) {
            String[] stateInfo = entry.split(",");
            adjacent.add(new Node(
                    stateInfo[0].trim(),
                    Float.parseFloat(stateInfo[1].trim())));
        }
        adjacent.sort(Comparator.comparing(Node::getName));
        struct.states.put(stateName, adjacent);
    }

    static void readHeuristic(Search struct) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(struct.pathToHeuristicFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split(":");
                String name = parts[0].trim();
                float value = Float.parseFloat(parts[1].trim());
                struct.heuristic.add(new Node(name, value));
                struct.heuristicByState.put(name, value);
            }
        }
        struct.heuristic.sort(Comparator.comparing(node -> node.name));
    }
}
