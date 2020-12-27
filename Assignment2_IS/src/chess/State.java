package chess;

// This class contains the information needed to represent a state 
public class State {

    public int[][] m_board = null;
    public Position m_agentPos = null;
    public int m_agent = -1; // the type of piece
    public int m_color = 0; // 0 for white, 1 for black
    public int m_boardSize = -1;
    public int[] numPieces;
    public boolean isJaqueW; //Las blancas están dando jaque
    public boolean isJaqueB; //Las negras están dando jaque

    // constructor
    public State(int[][] board, int[] numPieces) {//, Position pos, int a){
        m_board = board;
        this.numPieces = numPieces;
        m_boardSize = board[0].length;
    }

    public boolean isFinal() {
        return numPieces[5] == 0 || numPieces[11] == 0;
    }
    // hard copy of an State

    public State copy() {
        int[][] cBoard = new int[this.m_boardSize][this.m_boardSize];

        for (int r = 0; r < this.m_boardSize; r++) {
            System.arraycopy(this.m_board[r], 0, cBoard[r], 0, this.m_boardSize);
        }
        int[] cNumPieces = new int[12];
        System.arraycopy(this.numPieces, 0, cNumPieces, 0, 12);

        return new State(cBoard, cNumPieces);//, this.m_agentPos.copy(),m_agent);
    }

    // apply a given action over the current state -which remains unmodified. Return a new state
    public State applyAction(Action action) {
        State newState = this.copy();
        newState.m_board[action.m_initPos.row][action.m_initPos.col] = Utils.empty;
        newState.m_board[action.m_finalPos.row][action.m_finalPos.col] = newState.m_agent;
        newState.m_agentPos = action.m_finalPos;

        return newState;
    }
}
