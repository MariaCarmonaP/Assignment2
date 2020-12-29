package chess.adversarialSearch;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import java.util.ArrayList;

public class Minimax extends Adversarial {
    
    //boolean player = true; //true = max, false = min
    double[] valores;

    public Minimax(int maxDepth, int maxTurns) {
        super(maxDepth, maxTurns);
    }

    public int utility(State s) {
        int value = 0;
        for (int i = 0; i < s.numPieces.length; i++) {
            value += s.numPieces[i] * valuePieces[i];
        }
        value += s.isJaque;
        return value;
    }

    public ArrayList<Action> movements(int color, State s) {
        ArrayList<Action> actions= new ArrayList<>();
        ArrayList<Action> allMovements = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (s.m_board[r][c]<12 && (s.m_color=Utils.getColorPiece(s.m_board[r][c])) == color) {
                    s.m_agent = s.m_board[r][c];
                    piece = choosePiece(s);
                    s.m_agentPos = new Position(r, c);
                    if ((actions = piece.getPossibleActions(s)) != null) {
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
        allMovements = movements(0, s);
        valores = new double[allMovements.size()];
        double value = maxValue(s, 0);
        for (int i = 0; i < allMovements.size(); i++) {
            if (valores[i] == value) {
                a = allMovements.get(i);
                System.out.println("Action: "+ a + "\tPiece: "+ s.m_agent+ "\tPosition: "+ s.m_agentPos+ "\tColor: "+s.m_color);
                s.m_agent=s.m_board[a.m_initPos.row][a.m_initPos.col];
                s.m_agentPos=a.m_initPos;
                s.m_color=Utils.getColorPiece(s.m_agent);
                System.out.println("Action: "+ a + "\tPiece: "+ s.m_agent+ "\tPosition: "+ s.m_agentPos+ "\tColor: "+s.m_color);
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
        if (dMax >= maxDepth) {
            return utility(s);
        }
        allMovements = movements(0, s);
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(allMovements.get(i)), dMax));
            if(dMax==1){
                valores[i] = value;
            }
        }
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
        if (dMin >= maxDepth) {
            return utility(s);
        }
        allMovements = movements(1, s);
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.min(value, maxValue(s.applyAction(allMovements.get(i)), dMin));
        }
        return value;
    }
}
