
package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class Pawn extends Piece{
    
    public Pawn(int color) {
        this.color = color;
        if (color == 0) {
            type = Utils.wPawn;
        } else {
            type = Utils.bPawn;
        }
    }
    
    @Override
    public ArrayList<Action> getPossibleActions(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(4);
        int r = p.row;
        int c = p.col;
        
        if(color == 0){ //white pawn
            //standard pawn move
            if(r<7 && state.m_board[r + 1][c] == Utils.empty){
                list.add(new Action(p, new Position(r + 1, c)));
            }
            //starting pawn move
            if ((r == 1) && (state.m_board[r + 2][c] == Utils.empty) && (state.m_board[r + 1][c] == Utils.empty)) {
                list.add(new Action(p, new Position(r + 2, c)));
            }
            //pawn eats piece to the left
            if(r < 7 && c > 0 && (state.m_board[r + 1][c - 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r + 1][c - 1]) == 1)){
                list.add(new Action(p, new Position(r + 1, c - 1)));
            }
            //pawn eats piece to the right
            if(r < 7 && c < 7 && (state.m_board[r + 1][c + 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r + 1][c + 1]) == 1)){
                list.add(new Action(p, new Position(r + 1, c + 1)));
            }
        } else { //black pawn
            //standard pawn move
            if(r > 0 && state.m_board[r - 1][c] == Utils.empty){
                list.add(new Action(p, new Position(r - 1, c)));
            }
            //starting pawn move
            if ((r == 6) && (state.m_board[r - 2][c] == Utils.empty) && (state.m_board[r - 1][c] == Utils.empty)) {
                list.add(new Action(p, new Position(r - 2, c)));
            }
            //pawn eats piece to the left
            if(r > 0 && c > 0 && (state.m_board[r - 1][c - 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r - 1][c - 1]) == 0)){
                list.add(new Action(p, new Position(r - 1, c - 1)));
            }
            //pawn eats piece to the right
            if(r > 0 && c < 7 && (state.m_board[r - 1][c + 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r - 1][c + 1]) == 0)){
                list.add(new Action(p, new Position(r - 1, c + 1)));
            }
        }
        list.trimToSize();
        return list;
    }
}
