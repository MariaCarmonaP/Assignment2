package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(int color) {
        this.color = color;
        if (color == 0) {
            type = Utils.wRook;
        } else {
            type = Utils.bRook;
        }
    }

    @Override
    public ArrayList<Action> getPossibleActions(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(14);

        list.addAll(getHorizontalLeftMoves(state, p));
        list.addAll(this.getHorizontalRightMoves(state, p));
        list.addAll(this.getVerticalDownMoves(state, p));
        list.addAll(this.getVerticalUpMoves(state, p));
        list.trimToSize();
        return list;
    }
}
