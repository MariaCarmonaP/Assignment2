
package chess.search;

import chess.Action;
import chess.State;
import java.util.ArrayList;

public class IterativeDeepening extends Search{
    public IterativeDeepening(State s){
        super(s);
    }
    @Override
    public ArrayList<Action> doSearch() {
        DepthLimited dl;
        ArrayList<Action> sol;
        for(int depth=0;depth<=Integer.MAX_VALUE;depth++){
            dl = new DepthLimited(this.current.s, depth);
            sol = dl.doSearch();
            this.N_EXPLO += dl.N_EXPLO;
            this.N_EXPAND += dl.N_EXPAND;
            this.N_NODES += dl.N_NODES;
            this.current.cost = dl.current.cost;
            if(sol!=null||dl.cutoff==false) return sol;
        }
        return null;
    }
}
