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
public class Adversarial {
    public static int[] valuePieces = {1, 5, 3, 3, 9, 0, 1, 5, 3, 3, 9, 0};
    Piece piece = null;
    int depth = 0;
    int turns = 0;
    int maxDepth;
    int maxTurns;
    
    public Adversarial(int maxDepth, int maxTurns) {
        this.maxDepth = maxDepth;
        this.maxTurns = maxTurns;
    }
    public ArrayList<Action> movements(int color, State s) {
        ArrayList<Action> actions = new ArrayList<>();
        ArrayList<Action> allMovements = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (s.m_board[r][c] < 12 && (s.m_color = Utils.getColorPiece(s.m_board[r][c])) == color) {
                    s.m_agent = s.m_board[r][c];
                    piece = choosePiece(s);
                    s.m_agentPos = new Position(r, c);
//                    if(piece instanceof Pawn)
//                    if (color == 0) {
//                        s.distFin[color] += r-1;
//                    } else {
//                        s.distFin[color] += 7 - (r+1);
//                    }

                    if ((actions = piece.getPossibleActions(s)) != null) {
                        allMovements.addAll(actions);
                    }
                }
            }
        }
        return allMovements;
    }
    public Action decision(State s, int color){
        return null;    //Nunca llega, se overridea
    }
    
        public double utility(State s) {
        double value = 0;
        for (int i = 0; i < s.numPieces.length; i++) {
            value += s.numPieces[i] * valuePieces[i];
        }
        if (s.m_color == 0) {
            value += s.distFin[0];
        } else {
            value += s.distFin[1];
        }
        value += s.isJaque;
        //System.out.println("" + value);
        return value;
    }
        
    public Piece choosePiece(State s0) {
        
        Piece p = null;
        
        switch (s0.m_agent) {
            case Utils.wRook:
                p = new Rook(0);
                break;
            case Utils.bRook:
                p = new Rook(1);
                break;
            case Utils.wPawn:
                p = new Pawn(0);
                break;
            case Utils.bPawn:
                p = new Pawn(1);
                break;
            case Utils.wBishop:
                p = new Bishop(0);
                break;
            case Utils.bBishop:
                p = new Bishop(1);
                break;
            case Utils.wKnight:
                p = new Knight(0);
                break;
            case Utils.bKnight:
                p = new Knight(1);
                break;
            case Utils.wQueen:
                p = new Queen(0);
                break;
            case Utils.bQueen:
                p = new Queen(1);
                break;
            case Utils.wKing:
                p = new King(0);
                break;
            case Utils.bKing:
                p = new King(1);
                break;
        }
        
        return p;
    }
}
