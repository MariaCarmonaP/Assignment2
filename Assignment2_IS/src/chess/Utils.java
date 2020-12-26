package chess;

import chess.pieces.Piece;
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
        int[] numPieces = {8,2,2,2,1,1,8,2,2,2,1,1,0};
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
        r = gen.nextInt((n * n) - 1);
        Position bkingPos = allPositions.remove(r);
        board[bkingPos.row][bkingPos.col] = Utils.bKing;
        numPieces[Utils.bKing] -= 1;
        // placing the rest of chess.pieces
        Position pos = null;
        for (int piece = 0; piece < diffPieces; piece++) {
            for (int j = 0; j < numPieces[piece]; j++) {
                if (gen.nextDouble() <= p) {
                    r = gen.nextInt(allPositions.size());
                    pos = allPositions.remove(r);
                    board[pos.row][pos.col] = piece;
                }
            }
        }
        // Creating the instance, i.e., the state
        State state = new State(board);
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
        State state = new State(board);
        return state;
    }

    /**
     * Get colour piece
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
     * This method generates a problem instance.
     *
     * @param n size of the board
     * @param p probability for each piece to be included
     * @param seed to initiate the random generator (for reproducibility)
     * @param agent the type of piece who will "play" the game (always white)
     * @return the initial state (board and agent)
     */
//    public static State getProblemInstance(int n, double p, int seed, int agent) {
//        int[] numPieces = {8, 2, 2, 2, 1, 1, 8, 2, 2, 2, 1, 1};
//        int[][] board = new int[n][n];
//        for (int r = 0; r < n; r++) {
//            for (int c = 0; c < n; c++) {
//                board[r][c] = Utils.empty;
//            }
//        }
//
//        Random gen = new Random(seed);
//
//        // adjusting the number of possible  pieces according to board's size
//        double f = (n * n) / 64.0;
//        for (int i = 0; i < numPieces.length; i++) {
//            numPieces[i] = (int) Math.round(numPieces[i] * f);
//        }
//
//        numPieces[agent] -= 1;
//        ArrayList<Position> allPositions = getAllBoardPositions(n);
//
//        // placing our agent in the first row, we know these are the first n elements in allPositions
//        int r = gen.nextInt(n);
//        Position agentPos = allPositions.remove(r);
//        board[agentPos.row][agentPos.col] = agent;
//
//        // placing the rest of pieces
//        Position pos = null;
//        for (int piece = 0; piece < diffPieces; piece++) {
//            for (int j = 0; j < numPieces[piece]; j++) {
//                if (gen.nextDouble() <= p) {
//                    r = gen.nextInt(allPositions.size());
//                    pos = allPositions.remove(r);
//                    board[pos.row][pos.col] = piece;
//                }
//            }
//        }
//
//        // Creating the instance, i.e., the state
//        State state = new State(board, agentPos, agent);
//
//        return state;
//    }

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
                if ((c == state.m_agentPos.col) && (r == state.m_agentPos.row)) {
                    System.out.print("*" + letters[state.m_board[r][c]] + "|");
                } else {
                    System.out.print(" " + letters[state.m_board[r][c]] + "|");
                }
            }
            //botton row
            System.out.println("  ");
            for (int i = 0; i < size; i++) {
                System.out.print("---");
            }
            System.out.println("--");
        }

    }
    
    // main to test the methods
    /*public static void main(String[] args) {
	
		State state = Utils.getProblemInstance(8, 1.0, 1771, Utils.wRook);
		
		Utils.printBoard(state); 
	} */
} // end of class

