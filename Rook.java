package edu.siena.csis225.notchess;

import java.util.ArrayList;
import java.awt.*;

/**
 * Rook piece in a game of chess
 * @author - feven mengistu
 * @version - Spring 2023
 */
public class Rook extends ChessPiece {
    protected myButton[][] b;
    protected boolean isValid = false;

    /**
     * Constructor
     * 
     * @param character representing the piece, it's color, its current x and y
     *                  position and target move's position
     * 
     */
    public Rook(char c, Color color, int x, int y, int movesX, int movesY) {
        super('R', color, x, y, movesY, movesY);
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
        return "R";
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
        if (Math.abs(movesX) >= 1 && Math.abs(movesX) <= 8 && Math.abs(y) <= 8 && Math.abs(movesY) >= 1) {
            isValid = true;
            if (isValid) {
                ChessPiece a = buttons[movesY][movesX].curr;
               
                if (a == null && x == movesX && y == movesY) {
                    isValid = true;
                }
                // check the destination pieces colors
                if (a != null && (a.getColor() != buttons[x][y].color && x == movesX || y == movesY)) {
                    isValid = true;
                }
            }
        }

        else {
            isValid = false;
        }
        return isValid;

    }

}
