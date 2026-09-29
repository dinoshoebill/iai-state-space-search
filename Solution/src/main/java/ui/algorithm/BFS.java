package ui.algorithm;

import ui.Node;
import ui.Search;
import ui.Utils;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class BFS {

    private BFS() {
    }

    public static AbstractMap.SimpleImmutableEntry<List<Node>, Integer> bfs(Search struct) {
        Node currentNode = new Node(struct.startingState, 0f, null);
        ArrayDeque<Node> open = new ArrayDeque<>();
        Set<String> openSet = new HashSet<>();
        Set<String> closed = new HashSet<>();

        open.addLast(currentNode);
        openSet.add(currentNode.getName());

        int count = 0;
        while (!open.isEmpty()) {
            currentNode = open.removeFirst();
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
                if (!openSet.contains(adjacent.getName()) && !closed.contains(adjacent.getName())) {
                    open.addLast(adjacent);
                    openSet.add(adjacent.getName());
                }
            }
        }

        return new AbstractMap.SimpleImmutableEntry<>(null, null);
    }
}
