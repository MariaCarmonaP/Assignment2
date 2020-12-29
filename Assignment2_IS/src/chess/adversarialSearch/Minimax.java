package chess.adversarialSearch;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import java.util.ArrayList;

public class Minimax extends Adversarial {


    boolean player = true; //true = max, false = min
    //ArrayList<Action> allMovements = new ArrayList<>();
    ArrayList<Action> toChooseFrom;
    double[] valores;

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

    public ArrayList<Action> movements(int color) {
        ArrayList<Action> actions= new ArrayList<>();
        ArrayList<Action> allMovements = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (state.m_board[r][c]<12 && (state.m_color=Utils.getColorPiece(state.m_board[r][c])) == color) {
                    state.m_agent = state.m_board[r][c];
                    piece = choosePiece(state);
                    state.m_agentPos = new Position(r, c);
                    if ((actions = piece.getPossibleActions(state)) != null) {
                        allMovements.addAll(actions);
                    }
                }
            }
        }
        return allMovements;
    }

    @Override
    public Action decision(State s) {
        ArrayList<Action> allMovements;
        Action a = null;
        //depth = 0;
        allMovements = movements(0);
        valores = new double[allMovements.size()];
        System.out.println("Numero de posibles acciones: "+ allMovements.size());
        //toChooseFrom = (ArrayList<Action>)allMovements.clone();
        double value = maxValue(s, 0);

        for (int i = 0; i < allMovements.size(); i++) {
            if (valores[i] == value) {
                a = allMovements.get(i);
                break;
            }
        }

        return a;
    }

    public double maxValue(State s, int dmax) {
        ArrayList<Action> allMovements;
        double value;
        int dMax=dmax+1;
        if (s.isFinal()) {
            return utility(s);
        }

        value = MIN_VALUE;
        
        //depth=depth+1;
        //System.out.println("MAX After IsFinal: "+ dMax);
        //System.out.println("Limit depht: "+ maxDepth);
        if (dMax >= maxDepth) {
            return utility(s);
        }
        
        //System.out.println("MAX After maxDepth: "+ dMax);
        
        allMovements = movements(0);
//        proban2 = allMovements.size();
//        System.out.println("Proban2: "+ proban2);
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(allMovements.get(i)), dMax));
            if(dMax==1){
                valores[i] = value;
            }
        }
        
        //System.out.println("MAX After for: "+ dMax);
        return value;
    }

    public double minValue(State s, int dmin) {
        ArrayList<Action> allMovements;
        double value;
        int dMin = dmin+1;
        if (s.isFinal()) {
            return utility(s);
        }

        value = MAX_VALUE;

        //depth=depth+1;
        //System.out.println("Min After IsFinal: "+ dMin);
        //System.out.println("Limit depht: "+ maxDepth);
        if (dMin >= maxDepth) {
            //System.out.println("Entra en if");
            return utility(s);
            
        }
        //System.out.println("MIN After maxDepth: "+ dMin);
        allMovements = movements(1);
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.min(value, maxValue(s.applyAction(allMovements.get(i)), dMin));
        }
        //System.out.println("MIN After for: "+ dMin);
        return value;

    }

}
