package ui;

import java.util.*;

public class Utils {

    public static void solutionNotFound(String algorithm) {
        System.out.println("# " + algorithm);
        System.out.println("[FOUND_SOLUTION]: no");
    }

    public static void printResult(AbstractMap.SimpleImmutableEntry<List<Node>, Integer> path, Search struct) {

        if(path.getKey() == null)
            solutionNotFound(struct.algorithm);

        float totalCost;

        if(struct.algorithm.equals("BFS"))
            totalCost = calculateTotalCost(path, struct.states);
        else
            totalCost = path.getKey().get(path.getKey().size() - 1).value;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.getKey().size(); i++) {
            sb.append(path.getKey().get(i).name);
            if (i != path.getKey().size() - 1)
                sb.append(" => ");
        }

        String pathString = sb.toString();
        System.out.println("# " + (struct.algorithm.equals("ASTAR") ? "A-STAR " + struct.pathToHeuristicFile : struct.algorithm));
        System.out.println("[FOUND_SOLUTION]: yes");
        System.out.println("[STATES_VISITED]: " + path.getValue());
        System.out.println("[PATH_LENGTH]: " + path.getKey().size());
        System.out.println("[TOTAL_COST]: " + totalCost);
        System.out.println("[PATH]: " + pathString);
    }

    public static float calculateTotalCost(AbstractMap.SimpleImmutableEntry<List<Node>, Integer> path, Map<String, List<Node>> states) {
        float totalCost = 0;
        for (int i = 0; i < path.getKey().size() - 1; i++) {
            final int index = i + 1;
            totalCost += states.get(path.getKey().get(i).name).stream().filter(node ->
                    node.name.equals(path.getKey().get(index).name)).findFirst().get().value;
        }

        return totalCost;
    }

    public static int getNodeIndex(List<Node> open, Node node) {
        for(int i = 0; i < open.size(); i++)
            if(open.get(i).getName().equals(node.getName()))
                return i;

        return -1;
    }
}
