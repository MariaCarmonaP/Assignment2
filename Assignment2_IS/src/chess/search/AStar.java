
package chess.search;

import chess.State;
import chess.comparators.AStarComparator;
import java.util.PriorityQueue;

public class AStar extends Search{

    public AStar(State s0) {
        super(s0);
        this.open = new PriorityQueue<>(new AStarComparator());
    }

}
