/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.adversarialSearch;

import chess.Action;
import chess.Position;
import chess.State;
import java.util.ArrayList;

/**
 *
 * @author maric
 */
public class Alphabeta extends Adversarial{
    boolean player = true; //true = max, false = min

    public Alphabeta(State s, int maxDepth, int maxTurns) {
        super(s,maxDepth, maxTurns);
    }
    
    
    public void movements(int color) {

        //Pasamos como parámetro el tablero actual.
        ArrayList<Action> allMovements = new ArrayList<Action>();

        int n = state.m_boardSize;              //Board size.

        int i = 0, row = 0, column = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (state.m_color == color) {                            
                    
                    state.m_agent = state.m_board[r][c];
                    
                    piece = choosePiece(state);

                    state.m_agentPos = new Position(r, c);
                    
                    allMovements.addAll(piece.getPossibleActions(state));
                }
            }
        }
    }
    
    

}
