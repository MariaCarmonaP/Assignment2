
package chess.adversarialSearch;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import java.util.ArrayList;

public class MiniMax {
    public static int[] valuePieces = {1,5,3,3,9,0,1,5,3,3,9,0};
    boolean player = true; //true = max, false = min
    State state;
    Piece piece = null;
    public MiniMax(State s) {
        this.state = s;
    }
    public int utility(State s){
        int value=0;
        for(int i = 0; i<s.numPieces.length;i++){
            value += s.numPieces[i]*valuePieces[i];
        }
        if(isJaque(s)) value+=5;
        return value;
    }
    public boolean isJaque(State s){
        
        return false;
    }

    public ArrayList<Action> movements(int color) {
        //Pasamos como parámetro el tablero actual.
        ArrayList<Action> allMovements = new ArrayList<>();
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
        return allMovements;
    }

    public Piece choosePiece(State s0) {
        Piece p = null;
        switch (s0.m_agent) {
            case Utils.wRook:
                p = new Rook(0);
                break;
            case Utils.bRook:
                p = new Rook(1);
                break;
            case Utils.wPawn:
                p = new Pawn(0);
                break;
            case Utils.bPawn:
                p = new Pawn(1);
                break;
            case Utils.wBishop:
                p = new Bishop(0);
                break;
            case Utils.bBishop:
                p = new Bishop(1);
                break;
            case Utils.wKnight:
                p = new Knight(0);
                break;
            case Utils.bKnight:
                p = new Knight(1);
                break;
            case Utils.wQueen:
                p = new Queen(0);
                break;
            case Utils.bQueen:
                p = new Queen(1);
                break;
            case Utils.wKing:
                p = new King(0);
                break;
            case Utils.bKing:
                p = new King(1);
                break;
        }
        return piece;
    }
}
