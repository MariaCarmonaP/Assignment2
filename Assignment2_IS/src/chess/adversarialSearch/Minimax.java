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


    

    @Override
    public Action decision(State s, int color) {
        ArrayList<Action> allMovements;
        Action a = null;
        allMovements = movements(color, s);
        s.distFin[color]=0;
        valores = new double[allMovements.size()];
        double value = maxValue(s, 0, color);
        for (int i = 0; i < allMovements.size(); i++) {
            if (valores[i] == value) {
                a = allMovements.get(i);
                s.m_agent = s.m_board[a.m_initPos.row][a.m_initPos.col];
                s.m_agentPos = a.m_initPos;
                s.m_color = Utils.getColorPiece(s.m_agent);
                break;
            }
        }
        return a;
    }

    public double maxValue(State s, int dmax, int color) {
        ArrayList<Action> allMovements;
        double value;
        int dMax = dmax + 1;
        if (s.isFinal()) {
            return utility(s);
        }
        value = MIN_VALUE;
        if (dMax >= maxDepth) {
            return utility(s);
        }
        allMovements = movements(color, s);
        if (color == 0) {
            color = 1;
        } else {
            color = 0;
        }
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(allMovements.get(i)), dMax, color));
            if (dMax == 1) {
                valores[i] = value;
            }
        }
        return value;
    }

    public double minValue(State s, int dmin, int color) {
        ArrayList<Action> allMovements;
        double value;
        int dMin = dmin + 1;
        if (s.isFinal()) {
            return utility(s);
        }
        value = MAX_VALUE;
        if (dMin >= maxDepth) {
            return utility(s);
        }
        allMovements = movements(color, s);
        if (color == 0) {
            color = 1;
        } else {
            color = 0;
        }
        for (int i = 0; i < allMovements.size(); i++) {

            value = Math.min(value, maxValue(s.applyAction(allMovements.get(i)), dMin, color));
        }

        return value;
    }
}
