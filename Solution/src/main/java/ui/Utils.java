package ui;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Utils {

    private Utils() {
    }

    public static void solutionNotFound(String algorithm) {
        System.out.println("# " + algorithm);
        System.out.println("[FOUND_SOLUTION]: no");
    }

    public static void printResult(AbstractMap.SimpleImmutableEntry<List<Node>, Integer> path, Search struct) {
        if (path.getKey() == null) {
            solutionNotFound(struct.algorithm);
            return;
        }

        float totalCost = struct.algorithm.equals("BFS")
                ? calculateTotalCost(path, struct.states)
                : path.getKey().get(path.getKey().size() - 1).value;

        StringBuilder pathBuilder = new StringBuilder();
        List<Node> nodes = path.getKey();
        for (int i = 0; i < nodes.size(); i++) {
            pathBuilder.append(nodes.get(i).name);
            if (i < nodes.size() - 1) {
                pathBuilder.append(" => ");
            }
        }

        String header = struct.algorithm.equals("ASTAR")
                ? "A-STAR " + struct.pathToHeuristicFile
                : struct.algorithm;
        System.out.println("# " + header);
        System.out.println("[FOUND_SOLUTION]: yes");
        System.out.println("[STATES_VISITED]: " + path.getValue());
        System.out.println("[PATH_LENGTH]: " + nodes.size());
        System.out.println("[TOTAL_COST]: " + totalCost);
        System.out.println("[PATH]: " + pathBuilder);
    }

    public static float calculateTotalCost(
            AbstractMap.SimpleImmutableEntry<List<Node>, Integer> path,
            Map<String, List<Node>> states) {
        List<Node> nodes = path.getKey();
        float totalCost = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            String from = nodes.get(i).name;
            String to = nodes.get(i + 1).name;
            for (Node edge : states.get(from)) {
                if (edge.name.equals(to)) {
                    totalCost += edge.value;
                    break;
                }
            }
        }
        return totalCost;
    }

    public static List<Node> buildPath(Node goal) {
        List<Node> path = new ArrayList<>();
        Node current = goal;
        while (current.parent != null) {
            path.add(current);
            current = current.parent;
        }
        path.add(current);
        Collections.reverse(path);
        return path;
    }

    public static int getNodeIndex(List<Node> open, Node node) {
        for (int i = 0; i < open.size(); i++) {
            if (open.get(i).getName().equals(node.getName())) {
                return i;
            }
        }
        return -1;
    }

    public static float heuristicFor(Search struct, String stateName) {
        Float value = struct.heuristicByState.get(stateName);
        if (value == null) {
            throw new IllegalStateException("Missing heuristic for state: " + stateName);
        }
        return value;
    }
}
