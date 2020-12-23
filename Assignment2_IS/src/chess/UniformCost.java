
package chess;

import java.util.PriorityQueue;

public class UniformCost extends Search{

    public UniformCost(State s0) {
        super(s0);
        this.open = new PriorityQueue<>(new UniformCostComparator());
    }

}
