package edu.siena.csis225.notchess;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
// The above is NOT complete, and possibly has extraneous items.  Do not use wildcards.

/**
 * The start of a framework for a generic class that might be used for chess
 * pieces
 * 
 * @author Ira Goldstein (framework)
 * @author
 * @verion Spring 2023
 */

abstract class ChessPiece extends Thread {
	public static char type; // An enumerated type. Each piece has a type {P, R, N, B, Q, K}
	public Color color; // An enumerated type. "White or Black"
	public int x;
	public int y; // The (row, col) position of a piece
	public int movesX; // An array of valid relative x,y postions that the piece may move
	public int  movesY; // For example 1,-1 would move the piece up one row and to the left

	/**
	 * Constructor - With icon
	 * 
	 * @param type     {P, R, N, B, Q, K}
	 * @param color    The color of the ChessPiece
	 * @param position The current position of the ChessPiece
	 * @param moves    Valid moves for this piece
	 *
	 */
	public ChessPiece(char type, Color color, int x, int y, int movesX, int movesY) {
		// super(container);

		this.type = type;
		this.color = color;
		this.x = x;
		this.y = y;
		this.movesX = movesX;
		this.movesY = movesY;

	}
	public ChessPiece(char c){
		this.type = c;
	}

	
	 boolean validMove(myButton[][] buttons, int x, int y, int movesX, int movesY) {
        // TODO Auto-generated method stub
        return false;
    }
	
	//abstract Boolean knockout(myButton[][] buttons);


	public Color getColor() {
		return color;
	}

	public static char getType(){
		return type;
	}

	

}