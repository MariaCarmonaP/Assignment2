package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
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
public class King extends Piece {

    // constructor
    public King(int color) {
        m_color = color;
     //   this.valor = 0;
        if (color == 0) {
            m_type = Utils.wKing;
        } else {
            m_type = Utils.bKing;
        }

    }

    @Override
    public ArrayList<Action> getPossibleActions(State state) {

        int c, r;
        c = state.m_agentPos.col;
        r = state.m_agentPos.row;
        Action action = null;

        if (m_color == 1) {//Black king... I will let this for now...

        }

        ArrayList<Action> list = new ArrayList<>(3);

        //if (m_color == 0) {// White king
        if (r + 1 < state.m_boardSize) {
            if (state.m_board[r + 1][c] == Utils.empty || ((state.m_board[r + 1][c] != Utils.empty) && (Utils.getColorPiece(state.m_board[r + 1][c]) == 1))) {
                list.add(new Action(state.m_agentPos, new Position(r + 1, c)));
            }
            if (c + 1 < state.m_boardSize && (state.m_board[r + 1][c + 1] == Utils.empty || ((state.m_board[r + 1][c + 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r + 1][c + 1]) == 1)))) {
                list.add(new Action(state.m_agentPos, new Position(r + 1, c + 1)));
            }
            if (c - 1 >= 0 && (state.m_board[r + 1][c - 1] == Utils.empty || ((state.m_board[r + 1][c - 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r + 1][c - 1]) == 1)))) {
                list.add(new Action(state.m_agentPos, new Position(r + 1, c - 1)));
            }
        }
        if (r - 1 >= 0) {
            if (c - 1 >= 0 && (state.m_board[r - 1][c - 1] == Utils.empty || ((state.m_board[r - 1][c - 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r - 1][c - 1]) == 1)))) {
                list.add(new Action(state.m_agentPos, new Position(r - 1, c - 1)));
            }
            if (state.m_board[r - 1][c] == Utils.empty || ((state.m_board[r - 1][c] != Utils.empty) && (Utils.getColorPiece(state.m_board[r - 1][c]) == 1))) {
                list.add(new Action(state.m_agentPos, new Position(r - 1, c)));
            }
            if (c + 1 < state.m_boardSize && (state.m_board[r - 1][c + 1] == Utils.empty || ((state.m_board[r - 1][c + 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r - 1][c + 1]) == 1)))) {
                list.add(new Action(state.m_agentPos, new Position(r - 1, c + 1)));
            }
        }
        if (c + 1 < state.m_boardSize && (state.m_board[r][c + 1] == Utils.empty || ((state.m_board[r][c + 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r][c + 1]) == 1)))) {
            list.add(new Action(state.m_agentPos, new Position(r, c + 1)));
        }
        if (c - 1 >= 0 && (state.m_board[r][c - 1] == Utils.empty || ((state.m_board[r][c - 1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r][c - 1]) == 1)))) {
            list.add(new Action(state.m_agentPos, new Position(r, c - 1)));
        }
        //}

        return list;
    }
}
