package chess.adversarialSearch;

import chess.Action;
import chess.Position;
import chess.State;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import java.util.ArrayList;

public class Minimax extends Adversarial {


    boolean player = true; //true = max, false = min
    ArrayList<Action> allMovements = new ArrayList<>();
    ArrayList<Action> toChooseFrom = new ArrayList<>();

    public Minimax(State s, int maxDepth, int maxTurns) {
        super(s, maxDepth, maxTurns);
    }

    public int utility(State s) {
        //System.out.println("Entra en utility");
        int value = 0;
        for (int i = 0; i < s.numPieces.length; i++) {
            value += s.numPieces[i] * valuePieces[i];
        }
        value += s.isJaque;
        return value;
    }

    public void movements(int color) {
        ArrayList<Action> actions;
        int n = state.m_boardSize;              //Board size.
        //int i = 0, row = 0, column = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (state.m_color == color && state.m_board[r][c]!=12) {
                    state.m_agent = state.m_board[r][c];
                    piece = choosePiece(state);
                    state.m_agentPos = new Position(r, c);
                    if ((actions = piece.getPossibleActions(state)) != null) {
                        allMovements.addAll(actions);
                    }
                }
            }
        }
        //return allMovements;
    }

    @Override
    public Action decision(State s) {

        Action a = null;
        //depth = 0;
        movements(0);
        
        for(int i= 0; i<allMovements.size();i++){
            toChooseFrom.set(i,allMovements.get(i));
        }
        double value = maxValue(s, 0);

        for (int i = 0; i < allMovements.size(); i++) {
            if (maxValue(s.applyAction(allMovements.get(i)), 1) == value) {
                a = allMovements.get(i);
                break;
            }
        }

        return a;
    }

    public double maxValue(State s, int dmax) {

        double value;
        int dMax=dmax+1;
        if (s.isFinal()) {
            return utility(s);
        }

        value = MIN_VALUE;
        
        //depth=depth+1;
        System.out.println("MAX After IsFinal: "+ dMax);
        //System.out.println("Limit depht: "+ maxDepth);
        if (dMax >= maxDepth) {
            return utility(s);
        }
        
        System.out.println("MAX After maxDepth: "+ dMax);
        movements(0);
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(allMovements.get(i)), dMax));
            
        }
        
        System.out.println("MAX After for: "+ dMax);
        return value;
    }

    public double minValue(State s, int dmin) {

        double value;
        int dMin = dmin+1;
        if (s.isFinal()) {
            return utility(s);
        }

        value = MAX_VALUE;

        //depth=depth+1;
        System.out.println("Min After IsFinal: "+ dMin);
        //System.out.println("Limit depht: "+ maxDepth);
        if (dMin >= maxDepth) {
            //System.out.println("Entra en if");
            return utility(s);
            
        }
        System.out.println("MIN After maxDepth: "+ dMin);
        movements(1);
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.min(value, maxValue(s.applyAction(allMovements.get(i)), dMin));
        }
        System.out.println("MIN After for: "+ dMin);
        return value;

    }

}
