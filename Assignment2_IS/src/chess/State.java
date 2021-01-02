package chess;

// This class contains the information needed to represent a state 
public class State {

    public int[][] m_board = null;
    public Position m_agentPos = null;
    public int m_agent = -1; // the type of piece
    public int m_color = 0; // 0 for white, 1 for black
    public int m_boardSize = -1;
    public int[] numPieces;
    public int[] distFin = new int[2];
    public int isJaque=0;   //Negativo si hay más piezas negras que blancas dando jaque,
                            //positivo si hay mas blancas que negras dando jaque
                            //0 si hay el mismo numero de piezas blancas y negras dando jaque
                            //se actualiza al obtener los movimientos.

    // constructor
    public State(int[][] board, int[] numPieces) {//, Position pos, int a){
        m_board = board;
        this.numPieces = numPieces;
        m_boardSize = 8;
        distFin[0] = 0;
        distFin[1] = 0;
    }

    public boolean isFinal() {
        return numPieces[5] == 0 || numPieces[11] == 0;
    }
    // hard copy of an State

    public State copy() {
        int[][] cBoard = new int[this.m_boardSize][this.m_boardSize];
        State nuevo;
        for (int r = 0; r < this.m_boardSize; r++) {
            System.arraycopy(this.m_board[r], 0, cBoard[r], 0, this.m_boardSize);
        }
        int[] cNumPieces = new int[12];
        System.arraycopy(this.numPieces, 0, cNumPieces, 0, 12);
        nuevo = new State(cBoard, cNumPieces);
        nuevo.m_agent = this.m_agent;
        nuevo.m_agentPos = this.m_agentPos.copy();
        nuevo.m_color = this.m_color;
        nuevo.isJaque = this.isJaque;
        return nuevo;//, this.m_agentPos.copy(),m_agent);
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
