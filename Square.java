public class Square {
    private int row;
    private int col;
    private char mark;

    public Square(int row, int col, char mark) {
        this.row = row;
        this.col = col;
        this.mark = mark;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "" + mark;
    }
}
