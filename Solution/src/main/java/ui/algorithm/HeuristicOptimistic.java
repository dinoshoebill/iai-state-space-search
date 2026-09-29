package ui.algorithm;

import ui.Node;
import ui.Search;
import ui.Utils;

import java.util.AbstractMap;
import java.util.List;

public final class HeuristicOptimistic {

    private HeuristicOptimistic() {
    }

    public static void checkHeuristicOptimistic(Search struct) {
        boolean isOptimistic = true;
        System.out.println("# HEURISTIC-OPTIMISTIC " + struct.pathToHeuristicFile);

        for (Node node : struct.heuristic) {
            struct.startingState = node.name;
            float startingStateHeuristic = node.value;
            AbstractMap.SimpleImmutableEntry<List<Node>, Integer> result = UCS.ucs(struct);
            float totalCost = Utils.calculateTotalCost(result, struct.states);

            if (node.value <= totalCost) {
                printHeuristicOptimisticResult(true, struct.startingState, startingStateHeuristic, totalCost);
            } else {
                isOptimistic = false;
                printHeuristicOptimisticResult(false, struct.startingState, startingStateHeuristic, totalCost);
            }
        }

        printHeuristicOptimisticConclusion(isOptimistic);
    }

    private static void printHeuristicOptimisticResult(
            boolean ok, String node, float nodeHeuristic, float totalCost) {
        String status = ok ? "[OK]" : "[ERR]";
        System.out.println("[CONDITION]: " + status + " h(" + node + ") <= h*: "
                + nodeHeuristic + " <= " + totalCost);
    }

    private static void printHeuristicOptimisticConclusion(boolean ok) {
        if (ok) {
            System.out.println("[CONCLUSION]: Heuristic is optimistic.");
        } else {
            System.out.println("[CONCLUSION]: Heuristic is not optimistic.");
        }
    }
}
