package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Pawn extends Piece {
	
	
	// constructor
	public Pawn( int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wPawn;
		else m_type = Utils.bPawn;
		
	}
	
	
	// this method must be completed with all the possible pieces
	
        @Override
	public ArrayList<Action> getPossibleActions(State state){
		
		int c,r;
		c = state.m_agentPos.col;
		r = state.m_agentPos.row;
		
		if (m_color == 1){//black pawn ... I will let this for now ...
		
		}
		
		ArrayList<Action> list = new ArrayList<>(4);
		
		if (m_color == 0){// white pawn
			if (state.m_board[r+1][c] == Utils.empty){//standard pawn move
				list.add( new Action(state.m_agentPos, new Position(r+1,c)) );
			}
			if ((r==1) && (state.m_board[r+2][c] == Utils.empty) && (state.m_board[r+1][c] == Utils.empty)){//starting pawn move
				list.add( new Action(state.m_agentPos, new Position(r+2,c)) );
			}
			if ((c>0) && (state.m_board[r+1][c-1] != Utils.empty) 
						&& (Utils.getColorPiece(state.m_board[r+1][c-1]) == 1)){//capture
				list.add( new Action(state.m_agentPos, new Position(r+1,c-1)) );
			}
			if ((c<(state.m_boardSize-1)) && (state.m_board[r+1][c+1] != Utils.empty) 
						&& (Utils.getColorPiece(state.m_board[r+1][c+1]) == 1)){//capture
				list.add( new Action(state.m_agentPos, new Position(r+1,c+1)) );
			}
		}
                list.trimToSize();
		return list;
	}
	
	
	
	
}
