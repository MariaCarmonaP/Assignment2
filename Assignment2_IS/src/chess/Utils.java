package chess;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Utils {

    // all the pieces
    public static final int wPawn = 0;
    public static final int wRook = 1;
    public static final int wBishop = 2;
    public static final int wKnight = 3;
    public static final int wQueen = 4;
    public static final int wKing = 5;
    public static final int bPawn = 6;
    public static final int bRook = 7;
    public static final int bBishop = 8;
    public static final int bKnight = 9;
    public static final int bQueen = 10;
    public static final int bKing = 11;
    public static final int empty = 12;

    // number of pieces
    public static final int diffPieces = 12;

    // name (and letter) of each piece
    public static final String[] names = {"wPawn", "wRook", "wBishop", "wKnight", "wQueen", "wKing",
        "bPawn", "bRook", "bBishop", "bKnight", "bQueen", "bKing"};
    public static final String[] letters = {"P", "R", "B", "N", "Q", "K", "p", "r", "b", "n", "q", "k", " "};
    // Note we use N for Knight instead of K (to avoid confussion with King)
    // Note we add " " for empty cell

    /**
     * This method generates a random board problem to play chess.
     *
     * @param p probability for each piece to be included
     * @param seed to initiate the random generator (for reproducibility)
     * @return the initial state (board)
     */
    public static State getChessInstancePosition(double p, int seed) {
        int n = 8;
        int[][] board = new int[n][n];
        int[] numPieces = {8, 2, 2, 2, 1, 1, 8, 2, 2, 2, 1, 1};
        int[] numPieces1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] = Utils.empty;
            }
        }
        Random gen = new Random(seed);
        ArrayList<Position> allPositions = getAllBoardPositions(n);
        // placing the two kings in random position
        int r = gen.nextInt(n * n);
        Position wkingPos = allPositions.remove(r);
        board[wkingPos.row][wkingPos.col] = Utils.wKing;
        numPieces[Utils.wKing] -= 1;
        numPieces[Utils.wKing]++;
        r = gen.nextInt((n * n) - 1);
        Position bkingPos = allPositions.remove(r);
        board[bkingPos.row][bkingPos.col] = Utils.bKing;
        numPieces[Utils.bKing] -= 1;
        numPieces[Utils.bKing]++;
        // placing the rest of chess.pieces
        Position pos = null;
        for (int piece = 0; piece < diffPieces; piece++) {
            for (int j = 0; j < numPieces[piece]; j++) {
                if (gen.nextDouble() <= p) {
                    r = gen.nextInt(allPositions.size());
                    pos = allPositions.remove(r);
                    board[pos.row][pos.col] = piece;
                    numPieces1[piece]++;
                }
            }
        }
        // Creating the instance, i.e., the state
        State state = new State(board, numPieces1);
        return state;
    }

    public static State getChessInstance() {
        int n = 8;
        int[][] board = new int[n][n];
// Pawn
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] = Utils.empty;
            }
        }
        for (int c = 0; c < n; c++) {
            board[1][c] = Utils.wPawn;
            board[n - 2][c] = Utils.bPawn;
        }
//white pieces
        board[0][0] = Utils.wRook;
        board[0][1] = Utils.wKnight;
        board[0][2] = Utils.wBishop;
        board[0][n - 1] = Utils.wRook;
        board[0][n - 2] = Utils.wKnight;
        board[0][n - 3] = Utils.wBishop;
        board[0][3] = Utils.wKing;
        board[0][4] = Utils.wQueen;
//black pieces
        board[n - 1][0] = Utils.bRook;
        board[n - 1][1] = Utils.bKnight;
        board[n - 1][2] = Utils.bBishop;
        board[n - 1][n - 1] = Utils.bRook;
        board[n - 1][n - 2] = Utils.bKnight;
        board[n - 1][n - 3] = Utils.bBishop;
        board[n - 1][3] = Utils.bKing;
        board[n - 1][4] = Utils.bQueen;
// Creating the instance, i.e., the state
        int[] numPieces = {8, 2, 2, 2, 1, 1, 8, 2, 2, 2, 1, 1};
        State state = new State(board, numPieces);
        return state;
    }

    /**
     * Get colour piece
     *
     * @param piece
     * @return
     */
    public static int getColorPiece(int piece) {
        if ((piece >= 0) && (piece <= 5)) {
            return 0; //white
        } else if ((piece > 5) && (piece <= 11)) {
            return 1; //black
        } else {
            System.out.println("\n** Error, wrong piece code\n");
            System.exit(0);
        }

        return -1; //never arrives here, just to avoid compilation error
    }

    /**
     * fill (by rows) an ArrayList with all the possible coordinates
     *
     * @param n size of the board
     * @return
     */
    public static ArrayList<Position> getAllBoardPositions(int n) {
        ArrayList<Position> pos = new ArrayList<>(n * n);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                pos.add(new Position(r, c));
            }
        }
        return pos;
    }

    /**
     * Print a state (board + agent)
     *
     * @param state
     */
    public static void printBoard(State state) {
        DecimalFormat df = new DecimalFormat("00");

        int size = state.m_boardSize;
        /*if (size>50){
			System.out.println("**Error, board too large to be text-printed ...\n");
			System.exit(0);
		}*/

        // upper row
        System.out.print("   ");
        for (int c = 0; c < size; c++) {
            System.out.print(df.format(c) + " ");
        }
        System.out.println();

        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println("--");

        // board
        for (int r = 0; r < size; r++) {
            System.out.print(df.format(r) + "|");
            for (int c = 0; c < size; c++) {
                System.out.print(" " + letters[state.m_board[r][c]] + "|");
            }
            //botton row
            System.out.println("  ");
            for (int i = 0; i < size; i++) {
                System.out.print("---");
            }
            System.out.println("--");
        }

    }
    
    public static Piece choosePiece(int pi) {

        Piece p = null;

        switch (pi) {
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

    // main to test the methods
    /*public static void main(String[] args) {
	
		State state = Utils.getProblemInstance(8, 1.0, 1771, Utils.wRook);
		
		Utils.printBoard(state); 
	} */
} // end of class

