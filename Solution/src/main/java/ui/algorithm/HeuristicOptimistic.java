package ui.algorithm;

import ui.Node;
import ui.Search;
import ui.Utils;

import java.util.*;

public class HeuristicOptimistic {
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

    public static void printHeuristicOptimisticResult(boolean ok, String node, float nodeHeuristic, float totalCost) {
        if(ok)
            System.out.println("[CONDITION]: [OK] h(" + node + ") <= h*: " + nodeHeuristic + " <= " + totalCost);
        else
            System.out.println("[CONDITION]: [ERR] h(" + node + ") <= h*: " + nodeHeuristic + " <= " + totalCost);
    }

    public static void  printHeuristicOptimisticConclusion(boolean ok) {
        if(ok)
            System.out.println("[CONCLUSION]: Heuristic is optimistic.");
        else
            System.out.println("[CONCLUSION]: Heuristic is not optimistic.");
    }
}
