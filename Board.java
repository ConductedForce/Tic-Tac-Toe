/*
 * Title: Board.java
 * Abstract: Board object for Tic Tac Toe game
 * Author: Brandon Engholm
 * Date: 2/11/17
 */

public class Board {
	String[][] board = new String[3][3]; // hardcoded initialization
	public class Tac{
		public int x; // both of these should be private
		public int y;
		public Tac(int x, int y){
			this.x = x;
			this.y = y;
		}
	};
	Tac[] table = new Tac[]{ new Tac(0,0), 
			new Tac(0,1), new Tac(0,2), 
			new Tac(1,0), new Tac(1,1), 
			new Tac(1,2), new Tac(2,0), 
			new Tac(2,1), new Tac(2,2)};
	
	public Tac[] getTable(){
		return table;
	}
	public boolean hasWon( String type ){
		for ( int i = 0; i<3; i++)
			if ( board[i][0].equals(type) && board[i][1].equals(type) && board[i][2].equals(type) )
				return true;
		for ( int i = 0; i<3; i++)
			if ( board[0][i].equals(type) && board[1][i].equals(type) && board[2][i].equals(type) )
				return true;
		if ( board[0][0].equals(type) && board[1][1].equals(type) && board[2][2].equals(type) )
			return true;
		if ( board[0][2].equals(type) && board[1][1].equals(type) && board[2][0].equals(type) )
			return true;
		return false;
	}
	public Board(){ // more hard code
		board[0][0] = "1";
		board[0][1] = "2";
		board[0][2] = "3";
		board[1][0] = "4";
		board[1][1] = "5";
		board[1][2] = "6";
		board[2][0] = "7";
		board[2][1] = "8";
		board[2][2] = "9";
	}
	public void move( String move, String type){
		board[table[Integer.parseInt(move)-1].x][table[Integer.parseInt(move)-1].y] = type;
	}
	public boolean gameover(){
		for (int i = 0; i<3; i++)
			for (int k = 0; k<3; k++)
				if ( !board[i][k].equals("X") && !board[i][k].equals("O") )
					return false;
		return true;
	} 
	public void output(){
		System.out.println("\n -----------\n| "
				+ board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |\n -----------\n| "
				+ board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |\n -----------\n| "
				+ board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |\n -----------\n");
		
	}
	public boolean contains( String character ){
		for (int i = 0; i<3; i++)
			for (int k = 0; k<3; k++)
				if ( board[i][k].equals( character ) )
					return true;
		return false;
	}
}