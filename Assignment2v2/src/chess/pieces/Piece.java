package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class Piece {

    int color = -1;
    int type = -1;

    public ArrayList<Action> getPossibleActions(State state, Position p) {
        return null; //never arrive here
    }

    // horizontal left moves
    public ArrayList<Action> getHorizontalLeftMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;

        for (int c = c0 - 1; (c >= 0) && !busyCell; c--) {
            if (state.m_board[r0][c] == Utils.empty) {
                list.add(new Action(p, new Position(r0, c)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r0][c])) { // capture piece
                    list.add(new Action(p, new Position(r0, c)));
                }
            }
        }
        list.trimToSize();
        return list;
    }

    // horizontal right moves
    public ArrayList<Action> getHorizontalRightMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;

        for (int c = c0 + 1; (c < 8) && !busyCell; c++) {
            if (state.m_board[r0][c] == Utils.empty) {
                list.add(new Action(p, new Position(r0, c)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r0][c])) { // capture piece
                    list.add(new Action(p, new Position(r0, c)));
                }
            }
        }
        list.trimToSize();
        return list;
    }

    // vertical down moves
    public ArrayList<Action> getVerticalDownMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;

        for (int r = r0 + 1; (r < 8) && !busyCell; r++) {
            if (state.m_board[r][c0] == Utils.empty) {
                list.add(new Action(p, new Position(r, c0)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r][c0])) { // capture piece
                    list.add(new Action(p, new Position(r, c0)));
                }
            }
        }
        list.trimToSize();
        return list;
    }

    // vertical up moves
    public ArrayList<Action> getVerticalUpMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;

        for (int r = r0 - 1; (r >= 0) && !busyCell; r--) {
            if (state.m_board[r][c0] == Utils.empty) {
                list.add(new Action(p, new Position(r, c0)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r][c0])) { // capture piece
                    list.add(new Action(p, new Position(r, c0)));
                }
            }
        }
        list.trimToSize();
        return list;
    }

    // diagonal right up moves
    public ArrayList<Action> getDiagonalRightUpMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;
        int r = r0 - 1,
            c = c0 + 1;
        
        while(r >= 0 && c < 8 && !busyCell){
            if (state.m_board[r][c] == Utils.empty) {
                list.add(new Action(p, new Position(r, c)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
                    list.add(new Action(p, new Position(r, c)));
                }
            }
            c++;
            r--;
        }
        list.trimToSize();
        return list;
    }

    // diagonal left up moves
    public ArrayList<Action> getDiagonalLeftUpMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;
        int r = r0 - 1,
            c = c0 - 1;
        
        while(r >= 0 && c >= 0 && !busyCell){
            if (state.m_board[r][c] == Utils.empty) {
                list.add(new Action(p, new Position(r, c)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
                    list.add(new Action(p, new Position(r, c)));
                }
            }
            c--;
            r--;
        }
        list.trimToSize();
        return list;
    }

    // diagonal right down moves
    public ArrayList<Action> getDiagonalRightDownMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;
        int r = r0 + 1,
            c = c0 + 1;
        
        while(r < 8 && c < 8 && !busyCell){
            if (state.m_board[r][c] == Utils.empty) {
                list.add(new Action(p, new Position(r, c)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
                    list.add(new Action(p, new Position(r, c)));
                }
            }
            c++;
            r++;
        }
        list.trimToSize();
        return list;
    }

    // diagonal left down moves
    public ArrayList<Action> getDiagonalLeftDownMoves(State state, Position p) {
        ArrayList<Action> list = new ArrayList<>(7);
        int r0 = p.row;
        int c0 = p.col;
        boolean busyCell = false;
        int r = r0 + 1,
            c = c0 - 1;
        
        while(r < 8 && c >= 0 && !busyCell){
            if (state.m_board[r][c] == Utils.empty) {
                list.add(new Action(p, new Position(r, c)));
            } else {
                busyCell = true;
                if (color != Utils.getColorPiece(state.m_board[r][c])) { // capture piece
                    list.add(new Action(p, new Position(r, c)));
                }
            }
            c--;
            r++;
        }
        list.trimToSize();
        return list;
    }
}
