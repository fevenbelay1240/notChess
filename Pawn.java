package edu.siena.csis225.notchess;

import java.util.ArrayList;
import java.awt.*;

/**
 * Pawn piece in a game of chess
 * 
 * @author - feven mengistu
 * @version - Spring 2023
 */

public class Pawn extends ChessPiece {
    protected myButton[][] b;
    protected boolean isValid = false;

    /**
     * Constructor
     * 
     * @param character representing the piece, it's color, its current x and y
     *                  position and target move's position
     * 
     */
    public Pawn(char c, Color color, int x, int y, int movesX, int movesY) {
        super('P', color, x, y, y, y);
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
        return "P";
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
        // if (x == movesX && y == movesY) {
        //     isValid = false;
        // }
        if (Math.abs(movesX) >= 1 && Math.abs(movesX) <= 8 && Math.abs(movesY) <= 8 && Math.abs(movesY) >= 1) {
           // isValid = true;
         //   if (isValid) {
                ChessPiece a = buttons[movesX][movesY].curr;
                System.out.println("pawn class " + a);
               // movesX++;
                //movesY++;
                System.out.println(x + " " + y);
                if (Math.abs(x - movesX) == 1 || Math.abs(y - movesY) == 0 ) {
                    System.out.println("A");
                    isValid = true;
                }
                // check the destination pieces colors
                
                else if (a != null && a.color == buttons[x][y].color) {
                    System.out.println("B");
                    isValid = true;
                 // buttons[movesX][movesY].curr =  a;
                }
           // }
        }

        else {
            isValid = false;
        }
        System.out.println(isValid);
        return isValid;
    }

}
