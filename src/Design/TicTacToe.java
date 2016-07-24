package Design;

import LeetCode.util.Printer;

import java.util.Scanner;

/**
 * Created by Tin on 7/17/16.
 */
/*
    Use case:
    1. initialize
    2. play
    3. check if win
    4. tie (board full)
    5. restart
     */
public class TicTacToe {
    enum CellState{
        EMPTY(' '), PLAYER_X('X'), PLAYER_O('O');
        char mark;
        CellState(char c){
            mark = c;
        }
        @Override
        public String toString(){
            return Character.toString(mark);
        }
    }
    class Player{
        String name;
        CellState cellState;
        Player(String name, CellState cellState){
            this.name = name;
            this.cellState = cellState;
        }
    }

    CellState[][] board;
    int nEmptyCells;

    Player player1;
    Player player2;
    Player currentPlayer;
    Player winner;


    public TicTacToe(){
        initGame();
    }
    private void initGame() {
        if (board == null) board = new CellState[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
        nEmptyCells = 9;
    }
    public void setPlayers(String name1, String name2){
        player1 = new Player(name1, CellState.PLAYER_X);
        player2 = new Player(name2, CellState.PLAYER_O);
        currentPlayer = player1;
    }

    // play
    public boolean isBoardFull(){
        return nEmptyCells==0;
    }
    public String whosTurn(){
        return currentPlayer.name;
    }
    public void printBoard(){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
    }
    public boolean validMore(int i, int j){
        return i>=0 && i<3 && j>=0 && j<3 && board[i][j]==CellState.EMPTY;
    }
    public void play(int i, int j){
        board[i][j] = currentPlayer.cellState;
        if(hasWon(currentPlayer)){
            setWinner(currentPlayer);
        }else{
            currentPlayer = currentPlayer==player1?player2:player1;
        }
    }
    private boolean hasWon(Player player){
        if(winner!=null) return winner==player;
        boolean diaWin = true;
        boolean antiDiaWin = true;
        for(int i=0;i<3;i++){
            boolean rowWin = true;
            boolean colWin = true;
            for(int j=0;j<3;j++){
                if(board[i][j]!=player.cellState) rowWin = false;
                if(board[j][i]!=player.cellState) colWin = false;
            }
            if(rowWin || colWin) return true;
            if(board[i][i]!=player.cellState) diaWin = false;
            if(board[i][2-i]!=player.cellState) antiDiaWin = false;
        }
        return diaWin || antiDiaWin;
    }
    public boolean hasWinner(){
        return winner!=null;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public void rematch(){
        initGame();
        currentPlayer = player1;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        System.out.println("player1 name:");
        String name1 = scanner.next();
        System.out.println("player2 name:");
        String name2 = scanner.next();
        ticTacToe.setPlayers(name1, name2);

        boolean play = true;
        while(play){
            ticTacToe.rematch();
            while(!ticTacToe.isBoardFull()){
                boolean played = false;
                int i, j;
                do{
                    ticTacToe.printBoard();
                    System.out.println(ticTacToe.whosTurn() + " is playing, please enter coordinates");
                    i = scanner.nextInt();
                    j = scanner.nextInt();
                }while(!ticTacToe.validMore(i, j));
                ticTacToe.play(i,j);
                if(ticTacToe.hasWinner()) break;
            }
            System.out.println("-----------");
            ticTacToe.printBoard();
            if(ticTacToe.isBoardFull()){
                System.out.println("Game tied!");
            }else{
                System.out.println(ticTacToe.getWinner().name+" won!");
            }
            System.out.println("Play again? (y/n)");
            play = scanner.next().equals("y");
        }
    }
}
