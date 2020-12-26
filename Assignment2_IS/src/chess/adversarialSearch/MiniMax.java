/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.adversarialSearch;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import java.util.ArrayList;

/**
 *
 * @author maric
 */
public class MiniMax {

    boolean player = true; //true = max, false = min
    State state;

    Piece piece = null;

    public MiniMax(State s) {
        this.state = s;
    }
    public int utility(State s){
        return 0;
    }
//    public int minimax() {
//        if (state.isFinal()) {
//
//        } else {
//            if (player) {
//
//            } else {
//
//            }
//        }
//    }

    public void movements(int color) {

        //Pasamos como parámetro el tablero actual.
        ArrayList<Action> allMovements = new ArrayList<Action>();

        int n = state.m_boardSize;              //Board size.

        int i = 0, row = 0, column = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (state.m_color == color) {

                    state.m_agent = state.m_board[r][c];

                    piece = choosePiece(state);

                    state.m_agentPos = new Position(r, c);

                    allMovements.addAll(piece.getPossibleActions(state));
                }
            }
        }
    }

    public Piece choosePiece(State s0) {

        Piece piece = null;

        switch (s0.m_agent) {
            case Utils.wRook:
                piece = new Rook(0);
                break;
            case Utils.bRook:
                piece = new Rook(1);
                break;
            case Utils.wPawn:
                piece = new Pawn(0);
                break;
            case Utils.bPawn:
                piece = new Pawn(1);
                break;
            case Utils.wBishop:
                piece = new Bishop(0);
                break;
            case Utils.bBishop:
                piece = new Bishop(1);
                break;
            case Utils.wKnight:
                piece = new Knight(0);
                break;
            case Utils.bKnight:
                piece = new Knight(1);
                break;
            case Utils.wQueen:
                piece = new Queen(0);
                break;
            case Utils.bQueen:
                piece = new Queen(1);
                break;
            case Utils.wKing:
                piece = new King(0);
                break;
            case Utils.bKing:
                piece = new King(1);
                break;
        }

        return piece;
    }

}
