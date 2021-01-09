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
import chess.pieces.Piece;
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
        
        turns++;
        if (turns > maxTurns) {
            return null;
        }
        
        for (int i = 0; i < allMovements.size(); i++) {
            if (valores[i] == value) {
                a = allMovements.get(i);
//                System.out.println("Action: " + a + "\tPiece: " + s.m_agent + "\tPosition: " + s.m_agentPos + "\tColor: " + s.m_color);
                s.m_agent = s.m_board[a.m_initPos.row][a.m_initPos.col];
                s.m_agentPos = a.m_initPos;
                s.m_color = Utils.getColorPiece(s.m_agent);
//                System.out.println("Action: " + a + "\tPiece: " + s.m_agent + "\tPosition: " + s.m_agentPos + "\tColor: " + s.m_color);
                break;
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
//        if (t == maxTurns) {
//            return utility(s);
//        }
        allMovements = movements(color, s);
        if (color == 0) {
            color = 1;
        } else {
            color = 0;
        }
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(allMovements.get(i)), alpha, beta, dMax, color));
            if (dMax == 1) {
                valores[i] = value;
            }
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
