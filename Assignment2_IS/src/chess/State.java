package chess;

// This class contains the information needed to represent a state 

public class State {
	public int[][] m_board = null;
	public Position m_agentPos = null;
	public int m_agent = -1; // the type of piece
	public int m_color = 0; // 0 for white, 1 for black
	public int m_boardSize = -1; 
	
	// constructor
	public State(int[][] board){//, Position pos, int a){
		m_board = board;
		//m_agentPos = pos;
		//m_agent = a;
		
		/*if (m_agent >11){
			System.out.println("\n*** Invalid piece ***\n");
			System.exit(0);
		}
		else if (m_agent >5) m_color = 1; // black
		*/
		m_boardSize = board[0].length;
	}

	// compares if the current state is final, i.e. the agent is in the last row
//	public boolean isFinal(){
//            return this.m_agentPos.row == this.m_boardSize-1;
//	}
	public boolean isFinal(){
            int count=0;
            for(int i=0; i<m_boardSize; i++){
                for(int j=0; j<m_boardSize; j++){
                    if(m_board[i][j]==5 ||m_board[i][j]==11){
                        count++;
                    }
                }
            }
            return count!=2;
        }
	// hard copy of an State
	public State copy(){
		int[][] cBoard = new int[this.m_boardSize][this.m_boardSize];
		
		for(int r=0;r<this.m_boardSize;r++)
			System.arraycopy(this.m_board[r], 0, cBoard[r], 0, this.m_boardSize);
		
		return new State(cBoard);//, this.m_agentPos.copy(),m_agent);
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
