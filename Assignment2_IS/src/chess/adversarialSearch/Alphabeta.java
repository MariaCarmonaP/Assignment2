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
import java.util.ArrayList;

/**
 *
 * @author maric
 */
public class Alphabeta extends Adversarial{
    boolean player = true; //true = max, false = min

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
    
    

}
