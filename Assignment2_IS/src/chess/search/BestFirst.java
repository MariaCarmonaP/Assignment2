
package chess.search;

import chess.State;
import chess.search.comparators.BestFirstComparator;
import java.util.PriorityQueue;

public class BestFirst extends Search{

    public BestFirst(State s0) {
        super(s0);
        this.open = new PriorityQueue<>(new BestFirstComparator());
    }

}
