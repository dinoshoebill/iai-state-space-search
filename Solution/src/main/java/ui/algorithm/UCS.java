package ui.algorithm;

import ui.Node;
import ui.Search;
import ui.Utils;

import java.util.*;

public class UCS {

    public static AbstractMap.SimpleImmutableEntry<List<Node>, Integer> ucs(Search struct) {

        Node currentNode = new Node(struct.startingState, 0f, null);

        List<Node> open = new ArrayList<>(); // open states
        HashSet<String> openSet = new HashSet<>(); // // this is for optimisation, stores only state name
        HashSet<String> closed = new HashSet<>(); // visited

        open.add(currentNode); // add starting node
        openSet.add(currentNode.getName());

        int count = 0;
        while(!open.isEmpty()) {
            currentNode = open.remove(0); // remove head
            openSet.remove(currentNode.getName());

            closed.add(currentNode.getName()); // add node to closed states
            count++; // increase count

            // target state found
            if(struct.targetStates.contains(currentNode.name)) {
                List<Node> path = new ArrayList<>();
                while(currentNode.parent != null) {
                    path.add(currentNode);
                    currentNode = currentNode.parent;
                }

                path.add(currentNode);
                Collections.reverse(path); // reverse list

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
                } else if(inOpen) {
                    int index = Utils.getNodeIndex(open, adjacentNodes);

                    if(open.get(index).getValue() > adjacentNodes.getValue())
                        open.set(index, adjacentNodes);
                }
            }

            Collections.sort(open);
        }

        return new AbstractMap.SimpleImmutableEntry<>(null, null);
    }
}
