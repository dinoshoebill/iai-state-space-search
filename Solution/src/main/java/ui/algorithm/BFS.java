package ui.algorithm;

import ui.Node;
import ui.Search;

import java.util.*;

public class BFS {
    public static AbstractMap.SimpleImmutableEntry<List<Node>, Integer> bfs(Search struct) {

        Node currentNode = new Node(struct.startingState, 0f, null);

        List<Node> open = new ArrayList<>();

        HashSet<String> openSet = new HashSet<>();
        HashSet<String> closed = new HashSet<>();

        open.add(currentNode);
        openSet.add(currentNode.getName());

        int count = 0;
        while(!open.isEmpty()) {

            currentNode = open.remove(0);
            openSet.remove(currentNode.getName());

            closed.add(currentNode.getName());

            count++;

            if(struct.targetStates.contains(currentNode.name)) {

                List<Node> path = new ArrayList<>();
                while (currentNode.parent != null) {
                    path.add(currentNode);
                    currentNode = currentNode.parent;
                }

                path.add(currentNode);
                Collections.reverse(path);

                return new AbstractMap.SimpleImmutableEntry<>(path, count);
            } else if(currentNode.name.equals("empty")) {
                continue;
            }

            // construct adjacent nodes
            for(Node node : struct.states.get(currentNode.name)) {
                Node adjacentNodes = new Node(node.name, node.value + currentNode.value, currentNode);

                boolean inOpen = openSet.contains(adjacentNodes.getName());
                boolean inClosed = closed.contains(adjacentNodes.getName());

                if(!inOpen && !inClosed) {
                    open.add(adjacentNodes);
                    openSet.add(adjacentNodes.getName());
                }
            }
        }

        return new AbstractMap.SimpleImmutableEntry<>(null, null);
    }
}
