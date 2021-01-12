
package chess;

public class State {
    public int[][] m_board = null;
    public int[] numPieces;
    // constructor
    public State(int[][] board, int[] numPieces) {//, Position pos, int a){
        m_board = board;
        this.numPieces = numPieces;
    }
    
    // hard copy of an State

    public State copy() {
        int[][] cBoard = new int[8][8];
        State nuevo;
        for (int r = 0; r < 8; r++) {
            System.arraycopy(this.m_board[r], 0, cBoard[r], 0, 8);
        }
        int[] cNumPieces = new int[12];
        System.arraycopy(this.numPieces, 0, cNumPieces, 0, 12);
        nuevo = new State(cBoard, cNumPieces);
        return nuevo;
    }
    
    public boolean isFinal() {
        return numPieces[Utils.bKing] == 0 || numPieces[Utils.wKing] == 0;
    }
    
    public State applyAction(Action action) {
        State newState = this.copy();
        if(newState.m_board[action.m_finalPos.row][action.m_finalPos.col] != Utils.empty){
            newState.numPieces[newState.m_board[action.m_finalPos.row][action.m_finalPos.col]]--;
        }
        newState.m_board[action.m_finalPos.row][action.m_finalPos.col] = newState.m_board[action.m_initPos.row][action.m_initPos.col];
        newState.m_board[action.m_initPos.row][action.m_initPos.col] = Utils.empty;
        return newState;
    }
    
}
