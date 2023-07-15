package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Search {

    public String algorithm;
    public String startingState;
    public String pathToStatesFile;
    public String pathToHeuristicFile;
    public Map<String, List<Node>> states = new TreeMap<>();
    public List<String> targetStates;
    public List<Node> heuristic = new ArrayList<>();
}
