package chess;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Queen extends Piece{
    // constructor
	public Queen( int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wQueen;
		else m_type = Utils.bQueen;
		
	}
        @Override
        public ArrayList<Action> getPossibleActions(State state){
		ArrayList<Action> list = null;
		
		list = this.getHorizontalLeftMoves(state);
		list.addAll(this.getHorizontalRightMoves(state));
		list.addAll(this.getVerticalDownMoves(state));
		list.addAll(this.getVerticalUpMoves(state));
                list.addAll(this.getDiagonalRightUpMoves(state));
                list.addAll(this.getDiagonalLeftUpMoves(state));
                list.addAll(this.getDiagonalRightDownMoves(state));
                list.addAll(this.getDiagonalLeftDownMoves(state));
		
		return list;
	}
}
