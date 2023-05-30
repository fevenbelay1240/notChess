package edu.siena.csis225.notchess;

import java.util.ArrayList;
import java.awt.*;

/*
 * Knight class that represents the Knight piece for the ChessPiece Object
 * 
 * @author - feven mengistu
 * @version - Spring 2023
 */
public class Knight extends ChessPiece {
    protected myButton[][] b;
    protected boolean isValid = false;
    int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    /**
     * Constructor
     * 
     * @param character representing the piece, it's color, its current x and y
     *                  position and target move's position
     * 
     */
    public Knight(char c, Color color, int x, int y, int movesX, int movesY) {
        super('N', color, x, y, movesY, movesY);
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
        return "N";
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
        int row = Math.abs(x - movesX), col = Math.abs(y - movesY); 

        if (Math.abs(movesX) >= 1 && Math.abs(movesX) < 8 && Math.abs(y) < 8 && Math.abs(movesY) >= 1) {
            isValid = true;
            if (isValid) {
                
                ChessPiece a = buttons[movesX][movesY].curr;
        
                if (a == null && row == 1 && col == 2 || row == 2 && col == 1){
                    isValid=true;
                }
                // check the destination pieces colors
                else if (a.color != buttons[x][y].color && a != null) {
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
