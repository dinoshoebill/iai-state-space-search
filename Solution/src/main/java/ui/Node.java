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

    public String getName() { return this.name; }

    public float getValue() { return this.value; }

    @Override
    public String toString() {
        return "[" + name + ", " + value + ", " + heuristicValue + "]";
    }

    @Override
    public int compareTo(Node node) {

        if(Float.compare(this.heuristicValue, node.heuristicValue) < 0)
            return -1;
        else if(Float.compare(this.heuristicValue, node.heuristicValue) == 0)
            if (Float.compare(this.value, node.value) < 0)
                return -1;
            else if (Float.compare(this.value, node.value) == 0)
                return -1;
            else
                return 1;

        return 1;
    }
}