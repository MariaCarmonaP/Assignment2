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
    boolean player = true; //true = max, false = min
    ArrayList<Action> allMovements = new ArrayList<>();

    public Alphabeta(int maxDepth, int maxTurns) {
        super(maxDepth, maxTurns);
    }
    
    
    public ArrayList<Action> movements(int color, State s) {
        ArrayList<Action> actions= new ArrayList<>();
        
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
        //System.out.println("" + value);
        return value;
    }
    
    //INCOMPLETO 
    public Action alphaBetaCutoff (State s) {
        Action a = null;
        
        double value = 0.0;
        
        //INCOMPLETO
        //value = maxValue (s, alpha, beta, depth);
        
        for (int i = 0; i < allMovements.size(); i++) {
            if (utility(s.applyAction(allMovements.get(i))) == value) {
                a = allMovements.get(i);
            }
        }
        
        return a;
    }

    
    public double maxValue(State s, double alpha, double beta, int depth) {
        
        //FALTA IF INICIAL
        
        if (depth == maxDepth) {
            utility(s);
        }
        
        double value = MIN_VALUE;
        
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.max(value, minValue(s.applyAction(allMovements.get(i)), alpha, beta, depth + 1));
            
            if (value >= beta) {
                return value;
            }
            
            alpha = Math.max(alpha, value);
        }
        
        return value;
    }
    
    public double minValue (State s, double alpha, double beta, int depth) {
        
        //FALTA IF INICIAL
        
        if (depth == maxDepth) {
            utility(s);
        }
        
        double value = MAX_VALUE;
        
        for (int i = 0; i < allMovements.size(); i++) {
            value = Math.min(value, maxValue(s.applyAction(allMovements.get(i)), alpha, beta, depth + 1));
            
            if (value >= alpha) {
                return value;
            }
            
            beta = Math.min (beta, value);
        }
        
        return value;
    }
    
    

}
