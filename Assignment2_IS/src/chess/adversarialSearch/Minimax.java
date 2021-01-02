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

    public double utility(State s) {
        double value = 0;
        for (int i = 0; i < s.numPieces.length; i++) {
            value += s.numPieces[i] * valuePieces[i];
        }
        if (s.m_color == 0) {
            value += s.distFin[0];
        } else {
            value += s.distFin[1];
        }
        value += s.isJaque;
        return value;
    }

    public ArrayList<Action> movements(int color, State s) {
        ArrayList<Action> actions = new ArrayList<>();
        ArrayList<Action> allMovements = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (s.m_board[r][c] < 12 && (s.m_color = Utils.getColorPiece(s.m_board[r][c])) == color) {
                    s.m_agent = s.m_board[r][c];
                    piece = choosePiece(s);
                    s.m_agentPos = new Position(r, c);
//                    if(piece instanceof Pawn)
//                    if (color == 0) {
//                        s.distFin[color] += r-1;
//                    } else {
//                        s.distFin[color] += 7 - (r+1);
//                    }

                    if ((actions = piece.getPossibleActions(s)) != null) {
                        allMovements.addAll(actions);
                    }
                }
            }
        }
        return allMovements;
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
                System.out.println("Action: " + a + "\tPiece: " + s.m_agent + "\tPosition: " + s.m_agentPos + "\tColor: " + s.m_color);
                s.m_agent = s.m_board[a.m_initPos.row][a.m_initPos.col];
                s.m_agentPos = a.m_initPos;
                s.m_color = Utils.getColorPiece(s.m_agent);
                System.out.println("Action: " + a + "\tPiece: " + s.m_agent + "\tPosition: " + s.m_agentPos + "\tColor: " + s.m_color);
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
