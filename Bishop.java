package edu.siena.csis225.notchess;

import java.util.ArrayList;
import java.awt.*;

/**
 * Bishop piece in a game of chess
 * 
 * @author - feven mengistu
 * @version - Spring 2023
 */
public class Bishop extends ChessPiece {

    protected myButton[][] b;
    protected boolean isValid = false;

    /**
     * Constructor
     * 
     * @param character representing the piece, it's color, its current x and y
     *                  position and target move's position
     * 
     */
    public Bishop(char c, Color color, int x, int y, int movesX, int movesY) {
        super('B', color, x, y, movesY, movesY);
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /*
     * method to get the piece
     * 
     * @return - piece string
     */
    public String getPieceStr() {
        return "B";
    }

    /*
     * method to return the piece in a string form
     * 
     * @return - piece string
     */
    @Override
    public String toString() {
        return getPieceStr();
    }

    /*
     * method to return the color of the piece
     * 
     * @return - color of the piece
     */
    public Color col() {
        return color;
    }
    /*
     * method to check if the first and second pressed button are valid for the type
     * of piece
     * 
     * @param - buttons 2D array, current x and y position and target x and y
     * position
     * 
     * @return - true if move is valid
     */

    @Override
    boolean validMove(myButton[][] buttons, int x, int y, int movesX, int movesY) {
        if (x == movesX && y == movesY) {
            isValid = false;
        }
		if(Math.abs(movesX - x) != Math.abs(movesY - y)){
            isValid = false;
		}
        if (Math.abs(movesX) >= 1 && Math.abs(movesX) < 8 && Math.abs(y) < 8 && Math.abs(movesY) >= 1) {
            //isValid = true;

            ChessPiece a = buttons[movesX][movesY].curr;
            if (Math.abs(x-movesX) == Math.abs(y-movesY) && a == null) {
                isValid = true;
            }
            // check the destination pieces colors
          else  if (a != null && Math.abs(x-movesX) == Math.abs(y-movesY) && a.color != buttons[x][y].color ) {
                isValid = true;
            }

        }

        else {
            isValid = false;
        }
        return isValid;

    }
}
