package chess.adversarial;

import chess.Action;
import chess.State;
import java.util.ArrayList;

public class Alphabeta extends Adversarial {

    public Alphabeta(int maxDepth, int maxTurns) {
        super(maxDepth, maxTurns);
    }

    @Override
    public Action decision (State s, int color) {
        Action result;
        ArrayList<Action> mov = movements(s, color);
        result = firstMaxValue(s, mov, 0, color, Double.MIN_VALUE, Double.MAX_VALUE);
        return result;
    }

    public Action firstMaxValue(State s, ArrayList<Action> mov, int depth, int color, double alpha, double beta) {
        Action result = null;
        double value;
        double[] values = new double[mov.size()];
        int d = depth + 1;
        int c = (color == 0) ? 1 : 0;
        if (terminalTest(s, depth)) {
            return null;
        }
        value = Double.MIN_VALUE;
        for (int i = 0; i < mov.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(mov.get(i)), movements(s.applyAction(mov.get(i)), c), d, c, alpha, beta));
            values[i]=value;
            if(value>=beta){
                break;
            }
            alpha = Math.max(alpha, value);
        }
        for (int i = 0; i < mov.size(); i++) {
            if (values[i] == value) {
                result = mov.get(i);
            }
        }
        return result;
    }
    
    private double maxValue(State s, ArrayList<Action> mov, int depth, int color, double alpha, double beta) {
        double value;
        int d = depth + 1;
        int c = (color == 0) ? 1 : 0;
        if (terminalTest(s, depth)) {
            return utility(s, color);
        }
        value = Double.MIN_VALUE;
        for (int i = 0; i < mov.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(mov.get(i)), movements(s.applyAction(mov.get(i)), c), d, c, alpha, beta));
            if(value>=beta){
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    public double minValue(State s, ArrayList<Action> mov, int depth, int color, double alpha, double beta) {
        double value;
        int d = depth + 1;
        int c = (color == 0) ? 1 : 0;
        if (terminalTest(s, depth)) {
            return utility(s, color);
        }
        value = Double.MAX_VALUE;
        for (int i = 0; i < mov.size(); i++) {
            value = Math.min(value, maxValue(s.applyAction(mov.get(i)), movements(s.applyAction(mov.get(i)), c), d, c, alpha, beta));
            if(value<=alpha){
                return value;
            }
            alpha = Math.min(beta, value);
        }
        return value;
    }

    

}
