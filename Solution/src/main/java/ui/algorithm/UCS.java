package ui.algorithm;

import ui.Node;
import ui.Search;
import ui.Utils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class UCS {

    private UCS() {
    }

    public static AbstractMap.SimpleImmutableEntry<List<Node>, Integer> ucs(Search struct) {
        Node currentNode = new Node(struct.startingState, 0f, null);
        List<Node> open = new ArrayList<>();
        Set<String> openSet = new HashSet<>();
        Set<String> closed = new HashSet<>();

        open.add(currentNode);
        openSet.add(currentNode.getName());

        int count = 0;
        while (!open.isEmpty()) {
            currentNode = open.remove(0);
            openSet.remove(currentNode.getName());
            closed.add(currentNode.getName());
            count++;

            if (struct.targetStates.contains(currentNode.name)) {
                return new AbstractMap.SimpleImmutableEntry<>(Utils.buildPath(currentNode), count);
            }
            if (currentNode.name.equals("empty")) {
                continue;
            }

            for (Node neighbor : struct.states.get(currentNode.name)) {
                Node adjacent = new Node(neighbor.name, neighbor.value + currentNode.value, currentNode);
                boolean inOpen = openSet.contains(adjacent.getName());
                if (!inOpen && !closed.contains(adjacent.getName())) {
                    open.add(adjacent);
                    openSet.add(adjacent.getName());
                } else if (inOpen) {
                    int index = Utils.getNodeIndex(open, adjacent);
                    if (open.get(index).getValue() > adjacent.getValue()) {
                        open.set(index, adjacent);
                    }
                }
            }
            Collections.sort(open);
        }

        return new AbstractMap.SimpleImmutableEntry<>(null, null);
    }
}
