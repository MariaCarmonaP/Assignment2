package chess.pieces;


import chess.Action;
import chess.State;
import chess.Utils;
import java.util.ArrayList;

public class Bishop extends Piece{
    
    public Bishop( int color){
		m_color = color;
		this.valor=3;
		if (color==0) m_type = Utils.wBishop;
		else m_type = Utils.bBishop;
		
	}
    
    @Override
    public ArrayList<Action> getPossibleActions(State state){
		ArrayList<Action> list = null;

                list = this.getDiagonalRightUpMoves(state);
                list.addAll(this.getDiagonalLeftUpMoves(state));
                list.addAll(this.getDiagonalRightDownMoves(state));
                list.addAll(this.getDiagonalLeftDownMoves(state));
		
		return list;
	}
}