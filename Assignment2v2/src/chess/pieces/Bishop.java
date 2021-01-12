
package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int color) {
        this.color = color;
        if (color == 0) {
            type = Utils.wBishop;
        } else {
            type = Utils.bBishop;
        }
    }

    @Override
    public ArrayList<Action> getPossibleActions(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(13);

        list.addAll(this.getDiagonalRightUpMoves(state, p));
        list.addAll(this.getDiagonalLeftUpMoves(state, p));
        list.addAll(this.getDiagonalRightDownMoves(state, p));
        list.addAll(this.getDiagonalLeftDownMoves(state, p));
        list.trimToSize();
        return list;
    }
}
