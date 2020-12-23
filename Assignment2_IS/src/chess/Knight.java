package chess;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class Knight extends Piece {

    // constructor
    public Knight(int color) {
        m_color = color;

        if (color == 0) {
            m_type = Utils.wKnight;
        } else {
            m_type = Utils.bKnight;
        }

    }

    @Override
    public ArrayList<Action> getPossibleActions(State state) {
        int c0 = state.m_agentPos.col,
                r0 = state.m_agentPos.row, c;

        int[] arrC = new int[]{c0 - 2, c0 - 1, c0 + 2, c0 + 1};
        int[] arrR = new int[]{r0 - 2, r0 - 1, r0 + 2, r0 + 1};
        if (m_color == 1) {//black pawn ... I will let this for now ...

        }
        ArrayList<Action> list = new ArrayList<>(8);
        Action action;
        if (m_color == 0) {// white knight
            for (int r = 0; r < 4; r++) {
                c = (r + 1) % 4;
                if ((arrC[c] >= 0) && (arrC[c] < state.m_boardSize) && (arrR[r] >= 0) && (arrR[r] < state.m_boardSize)) {
                    if (state.m_board[arrR[r]][arrC[c]] == Utils.empty) {
                        action = new Action(state.m_agentPos, new Position(arrR[r], arrC[c]));
                        list.add(action);
                    } else if (isEnemy(state, arrR[r], arrC[c])) {
                        action = new Action(state.m_agentPos, new Position(arrR[r], arrC[c]));
                        list.add(action);
                    }
                }
                c = (r + 3) % 4;
                if ((arrC[c] >= 0) && (arrC[c] < state.m_boardSize) && (arrR[r] >= 0) && (arrR[r] < state.m_boardSize)) {
                    if (state.m_board[arrR[r]][arrC[c]] == Utils.empty) {
                        action = new Action(state.m_agentPos, new Position(arrR[r], arrC[c]));
                        list.add(action);
                    } else if (isEnemy(state, arrR[r], arrC[c])) {
                        action = new Action(state.m_agentPos, new Position(arrR[r], arrC[c]));
                        list.add(action);
                    }
                }
            }
        }
        return list;
    }

    private boolean isEnemy(State s, int r, int c) {
        return (Utils.getColorPiece(s.m_board[r][c]) != m_color);
    }
}
