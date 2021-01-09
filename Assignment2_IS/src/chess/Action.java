package chess;

import java.util.Objects;


// in this class we define a basic action for our problem. Going from position p0 to position p1.

public class Action {
	
	public Position m_initPos = null;
	public Position m_finalPos = null;
       // public boolean peonllego = false;
	
	public Action(Position p0, Position p1) {
		m_initPos = p0;
		m_finalPos = p1;
	}
	

	// the cost of a given action is: 1 + maximum of the horizontal/vertical traveled distance
	
	public double getCost(){
		return 1+Math.max(Math.abs(m_initPos.row-m_finalPos.row), Math.abs(m_initPos.col-m_finalPos.col));
	}
	
	
	// to String method, just for printing the solution
	
        @Override
	public String toString(){
		String s = "";
		
		s += "[ (" + m_initPos.row + "," + m_initPos.col + ") -> (" + 
				 + m_finalPos.row + "," + m_finalPos.col + ") ]";
		
		return s;
	}
        
        @Override
        public boolean equals (Object action) {
            boolean result = false;
            
            if (action instanceof Action) {
                result = ((Action) action).m_initPos.equals(this.m_initPos) && ((Action) action).m_finalPos.equals(this.m_finalPos);
            }
            
            return result;
        }

    /*@Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.m_initPos);
        hash = 83 * hash + Objects.hashCode(this.m_finalPos);
        return hash;
    }*/
	
} // end of class
 