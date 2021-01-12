package chess.adversarial;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import chess.pieces.Piece;
import java.util.ArrayList;

public abstract class Adversarial {

    int maxDepth = -1;
    int maxTurns = -1;
    public static int[] valuePieces = {1, 5, 3, 3, 9, 80, -1, -5, -3, -3, -9, -80};
    public Adversarial(int maxDepth, int maxTurns) {
        this.maxDepth = (maxDepth > maxTurns) ? maxTurns : maxDepth;
        this.maxTurns = maxTurns;
    }
    
    public double utility(State s, int color) {
        double value = 0;
        for (int i = 0; i < s.numPieces.length; i++) {
            if(color == 0){
                value += s.numPieces[i] * valuePieces[i];
            } else {
                value -= s.numPieces[i] * valuePieces[i];
            }
        }
        return value;
    }
    
    public ArrayList<Action> movements(State s, int color){
        ArrayList<Action> allMovements = new ArrayList<>();
        Piece piece;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if(s.m_board[r][c] < 12 && color == Utils.getColorPiece(s.m_board[r][c])){
                    piece = Utils.choosePiece(s.m_board[r][c]);
                    allMovements.addAll(piece.getPossibleActions(s, new Position(r, c)));
                }
            }
        }
        return allMovements;
    }
    
    public abstract Action decision (State s, int color);
    
    public boolean terminalTest(State s, int depth){
        return s.isFinal() || (depth >= maxDepth);
    }
}
