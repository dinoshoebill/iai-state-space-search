package ui;

import ui.algorithm.*;

public class Solution {

    public static void main(String[] args) {
        try {

            Search struct = new Search();

            int i = 0;
            do {
                switch (args[i]) {
                    case "--alg" -> struct.algorithm = args[++i].toUpperCase();
                    case "--ss" -> {
                        struct.pathToStatesFile = args[++i];
                        Reader.readData(struct);
                    }
                    case "--h" -> {
                        struct.pathToHeuristicFile = args[++i];
                        Reader.readHeuristic(struct);
                    }
                    case "--check-optimistic" -> HeuristicOptimistic.checkHeuristicOptimistic(struct);
                    case "--check-consistent" -> HeuristicConsistent.checkHeuristicConsistent(struct);
                }

                i++;
            } while (i < args.length);

            if(struct.algorithm != null) {
                switch (struct.algorithm.toUpperCase()) {
                    case "BFS" -> Utils.printResult(BFS.bfs(struct), struct);
                    case "UCS" -> Utils.printResult(UCS.ucs(struct), struct);
                    case "ASTAR" -> Utils.printResult(ASTAR.astar(struct), struct);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}

