package ui.algorithm;

import ui.Node;
import ui.Search;
import ui.Utils;

import java.util.List;
import java.util.Map;

public final class HeuristicConsistent {

    private HeuristicConsistent() {
    }

    public static void checkHeuristicConsistent(Search struct) {
        boolean isConsistent = true;
        System.out.println("# HEURISTIC-CONSISTENT " + struct.pathToHeuristicFile);

        for (Map.Entry<String, List<Node>> entry : struct.states.entrySet()) {
            String stateName = entry.getKey();
            float nodeHeuristic = Utils.heuristicFor(struct, stateName);

            for (Node adjacentNode : entry.getValue()) {
                if (adjacentNode.name.equals("empty")) {
                    break;
                }
                float adjacentHeuristic = Utils.heuristicFor(struct, adjacentNode.name);
                float edgeCost = adjacentNode.value;
                float required = adjacentHeuristic + edgeCost;

                if (nodeHeuristic <= required) {
                    printHeuristicConsistentResult(
                            true, stateName, adjacentNode.name, nodeHeuristic, adjacentHeuristic, edgeCost);
                } else {
                    isConsistent = false;
                    printHeuristicConsistentResult(
                            false, stateName, adjacentNode.name, nodeHeuristic, adjacentHeuristic, edgeCost);
                }
            }
        }

        printHeuristicConsistentConclusion(isConsistent);
    }

    private static void printHeuristicConsistentResult(
            boolean ok,
            String node,
            String adjacentNode,
            float nodeHeuristic,
            float adjacentHeuristic,
            float edgeCost) {
        String status = ok ? "[OK]" : "[ERR]";
        System.out.println("[CONDITION]: " + status + " h(" + node + ") <= h(" + adjacentNode + ") + c: "
                + nodeHeuristic + " <= " + adjacentHeuristic + " + " + edgeCost);
    }

    private static void printHeuristicConsistentConclusion(boolean ok) {
        if (ok) {
            System.out.println("[CONCLUSION]: Heuristic is consistent.");
        } else {
            System.out.println("[CONCLUSION]: Heuristic is not consistent.");
        }
    }
}
