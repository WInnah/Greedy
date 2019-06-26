import java.util.ArrayList;

public class Board {
    public Square[][] board;
    private int rows;
    private int cols;
    private int posRow;
    private int posCol;

    public Board(int rows, int cols, int posRow, int posCol) {
        this.rows = rows;
        this.cols = cols;
        this.posRow = posRow;
        this.posCol = posCol;
        board = new Square[rows][cols];
        setBoard();
    }

    public Square[][] getBoard() {
        return board;
    }

    public void setBoard() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (i == posRow && j == posCol){
                    board[i][j] = new Square(i, j, 'x');
                }else{
                    board[i][j] = new Square(i, j, 'o');
                }
            }
        }
    }

    public Square getSquare(int row, int col) {
        return board[row][col];
    }

    public ArrayList<Square> moves(Square q){
        ArrayList<Square> moves = new ArrayList<>(8);
        //upper left
        if (board[q.getRow()-2][q.getCol()-1] != null && board[q.getRow()-2][q.getCol()-1].getMark() != 'x'){
            moves.add(board[q.getRow()-2][q.getCol()-1]);
        }
        //upper right
        if (board[q.getRow()-2][q.getCol()+1] != null && board[q.getRow()-2][q.getCol()+1].getMark() != 'x'){
            moves.add(board[q.getRow()-2][q.getCol()+1]);
        }
        //right up
        if (board[q.getRow()-1][q.getCol()+2] != null && board[q.getRow()-1][q.getCol()+2].getMark() != 'x'){
            moves.add(board[q.getRow()-1][q.getCol()+2]);
        }
        //right down
        if (board[q.getRow()+1][q.getCol()+2] != null && board[q.getRow()+1][q.getCol()+2].getMark() != 'x'){
            moves.add(board[q.getRow()+1][q.getCol()+2]);
        }
        //lower right
        if (board[q.getRow()+2][q.getCol()+1] != null && board[q.getRow()+2][q.getCol()+1].getMark() != 'x'){
            moves.add(board[q.getRow()+2][q.getCol()+1]);
        }
        //lower left
        if (board[q.getRow()+2][q.getCol()-1] != null && board[q.getRow()+2][q.getCol()-1].getMark() != 'x'){
            moves.add(board[q.getRow()+2][q.getCol()-1]);
        }
        //left down
        if (board[q.getRow()+1][q.getCol()-2] != null && board[q.getRow()+1][q.getCol()-2].getMark() != 'x'){
            moves.add(board[q.getRow()+1][q.getCol()-2]);
        }
        //left up
        if (board[q.getRow()-1][q.getCol()-2] != null && board[q.getRow()-1][q.getCol()-2].getMark() != 'x'){
            moves.add(board[q.getRow()-1][q.getCol()-2]);
        }
        return moves;
    }

//    @Override
//    public String toString() {
//        String boardStr = "";
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                boardStr += board[i][j].getMark();
//            }
//            if (i < rows - 1) {
//                boardStr += "\n";
//            }
//        }
//        return boardStr;
//    }

    @Override
    public String toString(){
        return "bla bla bla";
    }
}
