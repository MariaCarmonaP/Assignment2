package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthLimited extends Search {

    int limit;
    boolean cutoff = false; //this is for the iterative deepening
    public DepthLimited(State s0, int l) {
        super(s0);
        this.limit = l;
        this.open = new Stack<>();
    }

    @Override
    public ArrayList<Action> doSearch() {
        N_EXPLO++;
        if (current.s.isFinal()) {
            return new ArrayList<>(0);
        }
        visited = new HashSet<>();
        visited.add(current.s.m_agentPos);
        successors = generateChildren();
        N_EXPAND++;
        open.addAll(successors);
        while (!open.isEmpty()) {
            current = extractNode(open);
            N_EXPLO++;
            if (!visited.contains(current.s.m_agentPos)) {
                if (current.s.isFinal()) {
                    return current.recoverPath();
                }
                if (current.depth < limit) {
                    successors = generateChildren();
                    N_EXPAND++;
                    open.addAll(successors);
                    visited.add(current.s.m_agentPos);
                } else {
                    cutoff = true;
                }
            }
        }
        return null;
    }
}
