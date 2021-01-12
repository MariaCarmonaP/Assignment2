package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int color) {
        this.color = color;
        if (color == 0) {
            type = Utils.wKnight;
        } else {
            type = Utils.bKnight;
        }
    }

    @Override
    public ArrayList<Action> getPossibleActions(State state, Position p) {
        int c0 = p.col;
        int r0 = p.row;
        int c;
        int[] arrC = new int[]{c0 - 2, c0 - 1, c0 + 2, c0 + 1};
        int[] arrR = new int[]{r0 - 2, r0 - 1, r0 + 2, r0 + 1};
        ArrayList<Action> list = new ArrayList<>(8);

        for (int r = 0; r < 4; r++) {
            c = (r + 1) % 4;
            if ((arrC[c] >= 0) && (arrC[c] < 8) && (arrR[r] >= 0) && (arrR[r] < 8)) {
                if (state.m_board[arrR[r]][arrC[c]] == Utils.empty
                        || (state.m_board[arrR[r]][arrC[c]] != Utils.empty
                        && isEnemy(state, arrR[r], arrC[c]))) {
                    list.add(new Action(p, new Position(arrR[r], arrC[c])));
                }
            }
            c = (r + 3) % 4;
            if ((arrC[c] >= 0) && (arrC[c] < 8) && (arrR[r] >= 0) && (arrR[r] < 8)) {
                if (state.m_board[arrR[r]][arrC[c]] == Utils.empty
                        || (state.m_board[arrR[r]][arrC[c]] != Utils.empty
                        && isEnemy(state, arrR[r], arrC[c]))) {
                    list.add(new Action(p, new Position(arrR[r], arrC[c])));
                }
            }
        }
        list.trimToSize();
        return list;
    }

    private boolean isEnemy(State s, int r, int c) {
        return (Utils.getColorPiece(s.m_board[r][c]) != color);
    }

}
