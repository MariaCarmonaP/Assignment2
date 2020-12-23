
package chess;

import java.util.LinkedList;

public class BreadthFirst extends Search{

    public BreadthFirst(State s0) {
        super(s0);
        this.open= new LinkedList<>();
    }

}
