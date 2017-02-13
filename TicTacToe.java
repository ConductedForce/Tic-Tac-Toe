/*
 * Title: TicTacToe.java
 * Abstract: A TicTacToe game in Java! With difficult AI.
 * Author: Brandon Engholm
 * Date: 2/10/17
 */

import java.util.Scanner;

public class TicTacToe {
	
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String choice = "";
		String characterType = ""; // Player's token
		String AICharacterType = ""; // AI's token
		Board board = new Board();
		
		String movedlast = ""; //last player to move
		boolean winningMove = false; // if the game has been won
		String whoWon = ""; // who won the game
		
		System.out.println("==== Welcome to Tic-Tac-Toe Game ===");
		System.out.print("Do you want to start first? (Y/N):");
		
		// enter decision loop
		retry:
		while ( true ){	
			choice = getInput(in);
			switch ( choice ){
				case "Y":
					characterType = "X";
					AICharacterType = "O";
					break;
				case "N":
					AICharacterType = "X";
					characterType = "O";
					break;
				default:
					System.out.println("Incorrect Input!");
					continue retry;
			}
			break;
		}
		// end decision loop
		
		System.out.println("OK! Your character is " + characterType + " and the computer character is " + AICharacterType);
		System.out.println("\n -----------\n| 1 | 2 | 3 |\n -----------\n| 4 | 5 | 6 |\n -----------\n| 7 | 8 | 9 |\n -----------");
	
		if (AICharacterType == "X"){
			moveAI( AICharacterType, board, characterType );
			movedlast = "AI";
			board.output();
		}else{
			movePlayer(in, characterType, board);
			movedlast = "Player";
			board.output();
		}
			
		// main game loop
		while ( !gameover( board ) ){
			if ( !movedlast.equals("AI") ){
				moveAI( AICharacterType, board, characterType);
				movedlast = "AI";
				board.output();
				if ( hasWon( AICharacterType, board) ){
					winningMove = true;
					whoWon = "AI";
					break;
				}
			}
			else{
				movePlayer(in, characterType, board);
				movedlast = "Player";
				board.output();
				if ( hasWon( characterType, board ) ){
					winningMove = true;
					whoWon = "Player";
					break;
				}
			}
		}
		
		if ( !winningMove )
			System.out.println("The game is a tie!");
		else
			System.out.println("The " + whoWon + " won the game!");
	
	}
	public static boolean hasWon( String type, Board board){
		return board.hasWon( type );
	}
	public static void moveAI(String AIType, Board board, String characterType){ // this whole method is nasty
		int AImove = 0;
		int[][] preferredMoves = {
		         {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
		         {0, 1}, {1, 0}, {1, 2}, {2, 1}};
		for (int[] move : preferredMoves) {
	         if (!board.board[move[0]][move[1]].equals(AIType) && !board.board[move[0]][move[1]].equals(characterType) ) { //bad way to access this but I was lazy
	        	for ( int i = 0; i < 9; i++)
	        		if ( move[0] == board.getTable()[i].x && move[1] == board.getTable()[i].y ){
	        			AImove = i+1;
	        			break;
	        		}
	        	break;
	         }
	      }
		System.out.println("OK! AI picks " + AImove);
		board.move( Integer.toString(AImove), AIType );
	}
	public static void movePlayer(Scanner in, String playerType, Board board){
		redo:
			while ( true ){	
				System.out.print("Pick your move: ");
				String choice = getInput(in);
				if (isMovePossible( board, choice, playerType))
					break;
				else {
					System.out.println("Move not possible!");
					continue redo;
				}
			}
	}
	public static boolean isMovePossible( Board board, String choice, String type ){
		switch (choice){
		default:
			break;
		case "1": if ( board.contains("1")) {board.move(choice, type);return true;} else break;
		case "2": if ( board.contains("2")) {board.move(choice, type);return true;} else break;
		case "3": if ( board.contains("3")) {board.move(choice, type);return true;} else break;
		case "4": if ( board.contains("4")) {board.move(choice, type);return true;} else break;
		case "5": if ( board.contains("5")) {board.move(choice, type);return true;} else break;
		case "6": if ( board.contains("6")) {board.move(choice, type);return true;} else break;
		case "7": if ( board.contains("7")) {board.move(choice, type);return true;} else break;
		case "8": if ( board.contains("8")) {board.move(choice, type);return true;} else break;
		case "9": if ( board.contains("9")) {board.move(choice, type);return true;} else break;
		}
		return false;
	}
	public static String getInput(Scanner in){
		String input = "";
		input = in.nextLine();
		return input;
	}
	public static boolean gameover( Board board ){
		return board.gameover();
	}
}