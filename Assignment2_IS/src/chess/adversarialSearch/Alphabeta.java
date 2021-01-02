/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.adversarialSearch;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import java.util.ArrayList;

/**
 *
 * @author maric
 */
public class Alphabeta extends Adversarial{
    double[] valores;

    public Alphabeta(int maxDepth, int maxTurns) {
        super(maxDepth, maxTurns);
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
    

    
    //INCOMPLETO 
    @Override
    public Action decision (State s, int color) {
        Action a = null;
        ArrayList<Action> allMovements = new ArrayList<>();
        double value = 0.0;
        allMovements = movements(color, s);
        s.distFin[color]=0;
        valores = new double[allMovements.size()];
        //INCOMPLETO
        value = maxValue (s, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, color);
        
        for (int i = 0; i < allMovements.size(); i++) {
            if (utility(s.applyAction(allMovements.get(i))) == value) {
                a = allMovements.get(i);
            }
        }
        
        return a;
    }

    
    public double maxValue(State s, double alpha, double beta, int depth, int color) {
        ArrayList<Action> allMovements = new ArrayList<>();
        double value;
        int dMax = depth + 1;
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
            value = Math.max(value, minValue(s.applyAction(allMovements.get(i)), alpha, beta, dMax, color));
            
            if (value >= beta) {
                return value;
            }
            
            alpha = Math.max(alpha, value);
        }
        
        return value;
    }
    
    public double minValue (State s, double alpha, double beta, int depth, int color) {
        ArrayList<Action> allMovements = new ArrayList<>();
        double value;
        int dMin = depth + 1;
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
            value = Math.min(value, maxValue(s.applyAction(allMovements.get(i)), alpha, beta, dMin, color));
            
            if (value >= alpha) {
                return value;
            }
            
            beta = Math.min (beta, value);
        }
        
        return value;
    }
    
    

}
