package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int color) {
        this.color = color;
        if (color == 0) {
            type = Utils.wQueen;
        } else {
            type = Utils.bQueen;
        }
    }

    @Override
    public ArrayList<Action> getPossibleActions(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(28);

        list.addAll(this.getHorizontalLeftMoves(state, p));
        list.addAll(this.getHorizontalRightMoves(state, p));
        list.addAll(this.getVerticalDownMoves(state, p));
        list.addAll(this.getVerticalUpMoves(state, p));
        list.addAll(this.getDiagonalRightUpMoves(state, p));
        list.addAll(this.getDiagonalLeftUpMoves(state, p));
        list.addAll(this.getDiagonalRightDownMoves(state, p));
        list.addAll(this.getDiagonalLeftDownMoves(state, p));
        list.trimToSize();
        return list;
    }
}
