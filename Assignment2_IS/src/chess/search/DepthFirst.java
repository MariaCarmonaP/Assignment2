
package chess.search;

import chess.State;
import java.util.Stack;

public class DepthFirst extends Search{

    public DepthFirst(State s0) {
        super(s0);
        this.open= new Stack<>();
    }

}