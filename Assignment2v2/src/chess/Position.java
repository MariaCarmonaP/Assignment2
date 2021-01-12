package chess;

public class Position {

    public int row;
    public int col;

    // constructor
    public Position(int r, int c) {
        row = r;
        col = c;
    }

    // compares if the received position is equals to the member one
    @Override
    public boolean equals(Object other) {
        if (other instanceof Position) {
            Position p = (Position) other;
            if (p.row != this.row) {
                return false;
            }
            return p.col == this.col;
        } else {
            System.out.println("Method equals for Position called with an object that is not a Position.\nThis method will return false but note that your code might not behave as it should.");
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 230000000 * hash + this.row;
        hash = 230000000 * hash + this.col;
        return hash;
    }

    // creates a hard copy of the object
    public Position copy() {
        return new Position(this.row, this.col);
    }

    @Override
    public String toString() {
        String s = "";
        s += "(" + row + "," + col + ")";
        return s;
    }
}
