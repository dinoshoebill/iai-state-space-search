package ui;

public class Node implements Comparable<Node> {

    public Node parent;
    public String name;
    public float value;
    public float heuristicValue;

    public Node(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public Node(String name, float value, Node parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;
    }

    public Node(String name, float value, float heuristicValue, Node parent) {
        this.name = name;
        this.value = value;
        this.heuristicValue = heuristicValue;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public float getHeuristicValue() {
        return heuristicValue;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + value + ", " + heuristicValue + "]";
    }

    @Override
    public int compareTo(Node other) {
        int byHeuristic = Float.compare(heuristicValue, other.heuristicValue);
        if (byHeuristic != 0) {
            return byHeuristic;
        }
        int byValue = Float.compare(value, other.value);
        return byValue != 0 ? byValue : -1;
    }
}
