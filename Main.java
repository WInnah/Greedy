import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("[1]Knight's Tour\n[2]Set Cover Problem\n[3]Coin Change Problem\n[4]Sorting an Array\nEnter choice: ");
        Scanner scan = new Scanner(System.in);
        Integer choice = scan.nextInt();
        if (choice == 1){
            /*Knight's tour. Implementation: If the moves from a location is equal to the other location
            first come first serve was applied (first store on the list is entertained first).
            Location are stored in the list in a clockwise manner (top, right, bottoem, left).*/
            //Time Complexity: 0(n^3)
            System.out.println("Enter board size");
            System.out.println("number of row: ");
            int row = scan.nextInt();
            System.out.println("number of column: ");
            int col = scan.nextInt();
            System.out.println("Enter Knight's initial location");
            System.out.println("row location: ");
            int ii = scan.nextInt();
            System.out.println("column location: ");
            int jj = scan.nextInt();
            int posRow = ii;
            int posCol = jj;
            boolean hasMoves = true;
            ArrayList<Point> curMoveList;
            ArrayList<Point> tempList = new ArrayList<>();
            ArrayList<Integer> storeList = new ArrayList<>();
            Character[][] board = new Character[row][col];

            //initialize board
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    if (i == posRow && j == posCol){
                        board[i][j] = 'x'; //mark for the path visited
                    }else{
                        board[i][j] = 'o'; //path unvisited
                    }
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
            //continue iteration until no move is possible.
            while(hasMoves){
                curMoveList = moves(posRow, posCol, board, row, col);
                storeList.clear();
                if (!curMoveList.isEmpty()){
                    for (int i = 0; i < curMoveList.size(); i++){
                        tempList = moves(((int) curMoveList.get(i).getX()), (int) curMoveList.get(i).getY(), board, row, col);
                        storeList.add(tempList.size());
                    }//compare number of possible moves, get minimum
                    int min = min(storeList);
                    int index = storeList.indexOf(min);
                    if (min > 0){//set and mark current path
                        board[(int) curMoveList.get(index).getX()][(int) curMoveList.get(index).getY()] = 'x';
                        posRow = (int) curMoveList.get(index).getX();
                        posCol = (int) curMoveList.get(index).getY();
                    }else {//mark end of path
                        board[(int) curMoveList.get(index).getX()][(int) curMoveList.get(index).getY()] = 'x';
                    }
                    print(board, row, col);
                }else{
                    hasMoves = false;
                }
            }

        }else if (choice == 2){
            /*Set Problem. Implementation: Count the of elements that is an element of the universe.
            * The largest will be the added to C. Elements of the universe that is already in C is removed.
            * Assumes that none of the elements of the sets in S is not an element of U*/
            //Time Complexity: O(n^3)
            Integer count = 0;
            Integer largeCount = 0;
            ArrayList<Integer> container = new ArrayList<>();
            ArrayList<Integer> U;
            ArrayList<Integer> temp;
            ArrayList<ArrayList<Integer>> S = new ArrayList<>();
            ArrayList<ArrayList<Integer>> C = new ArrayList<>();
            //space separate the elements when entering the list and end with a letter
            System.out.println("Enter list of universe: ");
            U = Tokenized();
            System.out.println("Enter number of sets: ");
            int marker = scan.nextInt();
            System.out.println("Enter sets (use letter to break)");
            for (int i = 0; i < marker; i++){
                temp = Tokenized();
                S.add(temp);
            }
            System.out.println("S: " + S);
            temp = U;
            while (!temp.isEmpty()){
                largeCount = 0;
                for (int i = 0; i < S.size(); i++) {
                    count = 0;
                    for (int j = 0; j < S.get(i).size(); j++) {
                        if (temp.contains(S.get(i).get(j))) {
                            count++;
                        }
                    }
                    if (count > largeCount) {
                        largeCount = count;
                    }
                    container.add(count);
                }
                if (largeCount != 0){
                    ArrayList<Integer> set = S.get(container.indexOf(largeCount));
                    C.add(set);
                    for (int i = 0; i < set.size(); i++){
                        if (temp.contains(set.get(i))){
                            temp.remove(set.get(i));
                        }
                    }
                }
                container.clear();
            }
            System.out.println("C: " + C);
        }else if (choice == 3){
            /*Coin Change Problem. Assumes that the given list of coins is
            already sorted and are all integer*/
            //Time Complexity: O(n)
            ArrayList<Integer> store = new ArrayList<Integer>();
            //space separate the elements when entering the list and end with a letter
            System.out.println("Enter a list of coins: (add a letter after the list)");
            ArrayList<Integer> S = new ArrayList<Integer>();
            S = Tokenized();
            System.out.println("Enter amount: ");
            Integer m = scan.nextInt();
            for (int i = 0; i< S.size(); i++){
                int temp = m/S.get(i);
                store.add(i, temp);
                temp *= S.get(i);
                m -= temp;
            }
            //System.out.println(store);
            for (int i = 0; i < store.size(); i++){
                if (!store.get(i).equals(0)) {
                    System.out.println(store.get(i) + " number of " + S.get(i)+" coin(s) ");
                }
            }
        }else {
            //Sorting Array
            //Time Complexity: O(n^2)
            //space separate the elements when entering the list and end with a letter
            System.out.println("Enter a list: (add a letter after the list)");
            ArrayList<Integer> S = new ArrayList<Integer>();
            S = Tokenized();
            Integer temp = 0;
            Integer initial = 0;
            System.out.println(S);
            for (int i = 0; i < S.size()-1; i++){
                initial = i;
                for (int j = i + 1; j < S.size(); j++){
                    if (S.get(j) < S.get(initial)){
                        initial = j;
                    }
                }
                temp = S.get(initial);
                S.set(initial, S.get(i));
                S.set(i, temp);
                System.out.println(S);
            }
        }
    }

    static ArrayList<Integer> Tokenized(){
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();
        while(scan.hasNextInt())
            list.add(scan.nextInt());
        return list;
    }

    static ArrayList<Point> moves(int row, int col, Character[][] board, int rowSize, int colSize){
        ArrayList<Point> moves = new ArrayList<>(8);
        //upper left
        if ((row-2 >= 0 && row-2 < rowSize) && (col-1 >= 0 && col-1 < colSize)){
            if (board[row-2][col-1] != 'x') {
                moves.add(new Point(row - 2, col - 1));
            }
        }
        //upper right
        if ((row-2 >= 0 && row-2 < rowSize) && (col+1 >= 0 && col+1 < colSize)){
            if (board[row-2][col+1] != 'x') {
                moves.add(new Point(row - 2, col + 1));
            }
        }
        //right up
        if ((row-1 >= 0 && row-1 < rowSize) && (col+2 >= 0 && col+2 < colSize)){
            if ( board[row-1][col+2] != 'x') {
                moves.add(new Point(row - 1, col + 2));
            }
        }
        //right down
        if ((row+1 >= 0 && row+1 < rowSize) && (col+2 >= 0 && col+2 < colSize)){
            if (board[row+1][col+2] != 'x') {
                moves.add(new Point(row + 1, col + 2));
            }
        }
        //lower right
        if ((row+2 >= 0 && row+2 < rowSize) && (col+1 >= 0 && col+1 < colSize)){
            if ( board[row+2][col+1] != 'x') {
                moves.add(new Point(row + 2, col + 1));
            }
        }
        //lower left
        if ((row+2 >= 0 && row+2 < rowSize) && (col-1 >= 0 && col-1 < colSize)){
            if (board[row+2][col-1] != 'x') {
                moves.add(new Point(row + 2, col - 1));
            }
        }
        //left down
        if ((row+1 >= 0 && row+1 < rowSize) && (col-2 >= 0 && col-2 < colSize)){
            if (board[row+1][col-2] != 'x') {
                moves.add(new Point(row + 1, col - 2));
            }
        }
        //left up
        if ((row-1 >= 0 && row-1 < rowSize) && (col-2 >= 0 && col-2 < colSize)){
            if (board[row-1][col-2] != 'x') {
                moves.add(new Point(row - 1, col - 2));
            }
        }
        return moves;
    }

    static Integer min(ArrayList<Integer> list){
        Integer min = list.get(0);
        for (int i = 0; i < list.size(); i++){
            if (list.get(i) < min && list.get(i) > 0){
                min = list.get(i);
            }
        }
        return min;
    }

    static void print(Character[][] board, int row, int col){
        System.out.println();
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
