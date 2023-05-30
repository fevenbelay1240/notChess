package edu.siena.csis225.notchess;

import java.util.ArrayList;
import java.awt.*;

/*
 * King class that represents the King piece for the ChessPiece Object
 * 
 * @author - feven mengistu
 * @version - Spring 2023
 */
public class King extends ChessPiece {

    protected myButton[][] b;
    protected boolean isValid = false;

    /**
     * Constructor
     * 
     * @param character representing the piece, it's color, its current x and y
     *                  position and target move's position
     * 
     */
    public King(char c, Color color, int x, int y, int movesX, int movesY) {
        super('K', color, x, y, movesX, movesY);
        this.color = color;
        this.x = x;
        this.y = y;
        this.movesX = movesX;
        this.movesY = movesY;
    }

    /*
     * method to get the piece
     * 
     * @return - piece string
     */
    public String getPieceStr() {
        return "K";
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
        int row, col;
        if (x == movesX && y == movesY) {
            isValid = false;
        }
        if (Math.abs(movesX) >= 1 && Math.abs(movesX) < 8 && Math.abs(y) < 8 && Math.abs(movesY) >= 1) {
      
                isValid = true;
                if (isValid) {
                    ChessPiece a = buttons[movesX][movesY].curr;
                    System.out.println("king class " + a);
                    if (a == null) {
                        isValid = true;
                    }
                    // check the destination pieces colors
                    else if (a.color != buttons[x][y].color) {
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