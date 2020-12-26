package chess.pieces;

import chess.Action;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece
public class Rook extends Piece {

    // constructor
    public Rook(int color) {
        m_color = color;
        this.valor = 5;
        if (color == 0) {
            m_type = Utils.wRook;
        } else {
            m_type = Utils.bRook;
        }

    }

    // this method must be completed with all the possible pieces
    @Override
    public ArrayList<Action> getPossibleActions(State state) {
        ArrayList<Action> list = null;

        list = this.getHorizontalLeftMoves(state);
        list.addAll(this.getHorizontalRightMoves(state));
        list.addAll(this.getVerticalDownMoves(state));
        list.addAll(this.getVerticalUpMoves(state));

        return list;
    }

}
