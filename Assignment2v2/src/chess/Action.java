package chess;

import java.util.Objects;

public class Action {

    Position m_initPos = null;
    Position m_finalPos = null;

    public Action(Position p0, Position p1) {
        m_initPos = p0;
        m_finalPos = p1;
    }

    @Override
    public String toString() {
        String s = "";
        s += "[ (" + m_initPos.row + "," + m_initPos.col + ") -> ("
                + +m_finalPos.row + "," + m_finalPos.col + ") ]";
        return s;
    }

    @Override
    public boolean equals(Object action) {
        boolean result = false;
        if (action instanceof Action) {
            result = ((Action) action).m_initPos.equals(this.m_initPos) && ((Action) action).m_finalPos.equals(this.m_finalPos);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61000 * hash + Objects.hashCode(this.m_initPos);
        hash = 61000 * hash + Objects.hashCode(this.m_finalPos);
        return hash;
    }
}
