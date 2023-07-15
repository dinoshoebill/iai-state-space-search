package ui.algorithm;

import ui.Node;
import ui.Search;

import java.util.List;
import java.util.Map;

public class HeuristicConsistent {
    public static void checkHeuristicConsistent(Search struct) {

        boolean isConsistent = true;

        System.out.println("# HEURISTIC-CONSISTENT " + struct.pathToHeuristicFile);

        for(Map.Entry<String, List<Node>> nodes : struct.states.entrySet()) {

            for(Node adjecentNode : nodes.getValue()) {
                if(adjecentNode.name.equals("empty")) break;
                float nodeHeuristic =  struct.heuristic.stream().filter((node) -> node.name.equals(nodes.getKey())).findFirst().get().value;
                float adjecentNodeHeuristic = struct.heuristic.stream().filter((node) -> node.name.equals(adjecentNode.name)).findFirst().get().value;
                float cost = adjecentNode.value;
                float totalCost = adjecentNodeHeuristic + cost;

                if(nodeHeuristic <= totalCost) {
                    printHeuristicConsistentResult(true, nodes.getKey(), adjecentNode.name, nodeHeuristic, adjecentNodeHeuristic, cost);
                } else {
                    isConsistent = false;
                    printHeuristicConsistentResult(false, nodes.getKey(), adjecentNode.name, nodeHeuristic, adjecentNodeHeuristic, cost);
                }
            }
        }

        printHeuristicConsistentConclusion(isConsistent);
    }

    public static void printHeuristicConsistentResult(boolean ok, String node, String adjecentNode, float nodeHeuristic, float adjecentNodeHeuristic, float totalCost) {
        if(ok)
            System.out.println("[CONDITION]: [OK] h(" + node + ") <= h(" + adjecentNode + ") + c: " + nodeHeuristic + " <= " + adjecentNodeHeuristic + " + " + totalCost);
        else
            System.out.println("[CONDITION]: [ERR] h(" + node + ") <= h(" + adjecentNode + ") + c: " + nodeHeuristic + " <= " + adjecentNodeHeuristic + " + " + totalCost);
    }

    public static void  printHeuristicConsistentConclusion(boolean ok) {
        if(ok)
            System.out.println("[CONCLUSION]: Heuristic is consistent.");
        else
            System.out.println("[CONCLUSION]: Heuristic is not consistent.");
    }
}
