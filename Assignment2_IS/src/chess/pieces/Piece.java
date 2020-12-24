package chess.pieces;

import chess.Action;
import chess.Position;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Piece {
	
	// members
	int m_color = -1;
	int m_type = -1;
	
	// this method must be overwritten by all the possible pieces
	
	public ArrayList<Action> getPossibleActions(State state){
			
		return null; //never arrive here
	}
	
	
	// horizontal left moves
	public ArrayList<Action> getHorizontalLeftMoves(State state){
		ArrayList<Action> list = new ArrayList<>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int c=c0-1; (c>=0) && !busyCell; c--){
			if (state.m_board[r0][c] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r0,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r0][c])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r0,c));
					list.add(action);
				}
			}
		}
		
		return list;
	}
	
	// horizontal right moves
	public ArrayList<Action> getHorizontalRightMoves(State state){
		ArrayList<Action> list = new ArrayList<>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int c=c0+1; (c<state.m_boardSize) && !busyCell; c++){
			if (state.m_board[r0][c] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r0,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r0][c])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r0,c));
					list.add(action);
				}
			}
		}
		
		return list;
	}
		
	// vertical down moves
	public ArrayList<Action> getVerticalDownMoves(State state){
		ArrayList<Action> list = new ArrayList<>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int r=r0+1; (r<state.m_boardSize) && !busyCell; r++){
			if (state.m_board[r][c0] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r,c0));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c0])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r,c0));
					list.add(action);
				}
			}
		}
		
		return list;
	}
	
	// vertical up moves
	public ArrayList<Action> getVerticalUpMoves(State state){
		ArrayList<Action> list = new ArrayList<>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int r=r0-1; (r>=0) && !busyCell; r--){
			if (state.m_board[r][c0] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r,c0));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c0])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r,c0));
					list.add(action);
				}
			}
		}
		
		return list;
	}
        public ArrayList<Action> getDiagonalRightUpMoves(State state){
            ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
                int r=r0-1,c = c0+1;
                
                Boolean busyCell = false;
                
                for(; (r>=0) && (c<state.m_boardSize) && !busyCell; r--){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r,c));
					list.add(action);
				}
			}
                        c++;
		}
                return list;
        }
        
        public ArrayList<Action> getDiagonalLeftUpMoves(State state){
            ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
                int r=r0-1,c = c0-1;
                
                Boolean busyCell = false;
                
                for(; (r>=0) && (c>=0) && !busyCell; r--){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r,c));
					list.add(action);
				}
			}
                        c--;
		}
                return list;
        }
        
        public ArrayList<Action> getDiagonalRightDownMoves(State state){
            ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
                int r=r0+1,c = c0+1;
                
                Boolean busyCell = false;
                
                for(; (r<state.m_boardSize) && (c<state.m_boardSize) && !busyCell; r++){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r,c));
					list.add(action);
				}
			}
                        c++;
		}
                return list;
        }
        
        public ArrayList<Action> getDiagonalLeftDownMoves(State state){
            ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = state.m_agentPos.col;
		r0 = state.m_agentPos.row;
		Action action = null;
                int r=r0+1,c = c0-1;
                
                Boolean busyCell = false;
                
                for(; (r<state.m_boardSize) && (c>=0) && !busyCell; r++){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(state.m_agentPos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(state.m_agentPos, new Position(r,c));
					list.add(action);
				}
			}
                        c--;
		}
                return list;
        }
		
	
	
}
