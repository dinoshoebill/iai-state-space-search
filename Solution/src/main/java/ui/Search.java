package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Search {

    public String algorithm;
    public String startingState;
    public String pathToStatesFile;
    public String pathToHeuristicFile;
    public Map<String, List<Node>> states = new TreeMap<>();
    public Set<String> targetStates;
    public List<Node> heuristic = new ArrayList<>();
    public Map<String, Float> heuristicByState = new HashMap<>();
}
