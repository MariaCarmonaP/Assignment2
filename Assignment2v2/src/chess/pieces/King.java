package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class King extends Piece {

    public King(int color) {
        this.color = color;
        if (color == 0) {
            type = Utils.wKing;
        } else {
            type = Utils.bKing;
        }
    }

    @Override
    public ArrayList<Action> getPossibleActions(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(8);
        int r0 = p.row;
        int c0 = p.col;

        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
                if (r != 0 || c != 0) {
                    if ((r0 + r) >= 0 && (r0 + r) <= 7
                            && (c0 + c) >= 0 && (c0 + c) <= 7
                            && (state.m_board[r0 + r][c0 + c] == Utils.empty
                            || (state.m_board[r0 + r][c0 + c] != Utils.empty
                            && Utils.getColorPiece(state.m_board[r0 + r][c0 + c]) != color))) {
                        list.add(new Action(p, new Position(r0 + r, c0 + c)));
                    }
                }
            }
        }
        list.trimToSize();
        return list;
    }
}
