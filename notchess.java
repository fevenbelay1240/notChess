package edu.siena.csis225.notchess;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
// The above is NOT complete and possibly has extraneous items.  Do not use wildcards.

/**
 * notchess CSIS-225
 *
 * @author Ira Goldstein (Framework)
 * @author feven mengistu
 * @version Spring 2023
 */
// extends somethingHelpfulThatWeSawInClass
public class notchess implements Runnable, ActionListener {

	/**
	 * The run method to set up the graphical user interface
	 */

	private JLabel moveLabel;
	private JLabel turnLabel;
	private JTextField inputBox;
	private JButton moveButton;
	private String buttonMove;
	private String textMove;
	private boolean whiteTurn = true;
	private boolean blackTurn = true;

	private int row1;
	private int row2;
	private Color myButtonReference;
	private Boolean firstPress = true;
	private myButton[][] squareButton = new myButton[9][9];
	private myButton aButton = new myButton(0, 0, null);
	private final String COLUMN[] = { " ", "A", "B", "C", "D", "E", "F", "G", "H", };
	private final String PIECES[] = { " ", "R", "N", "B", "Q", "K", "B", "N", "R" };
	private final String COLSTRING = "ABCDEFGH";
	ArrayList<ChessPiece> pWhite = new ArrayList<ChessPiece>();
	ArrayList<ChessPiece> pBlack = new ArrayList<ChessPiece>();
	ArrayList<ChessPiece> blackMoves = new ArrayList<ChessPiece>();
	ArrayList<ChessPiece> whiteMoves = new ArrayList<ChessPiece>();

	private String column2;
	protected ChessPiece chess;// = new King.getPieceStr();= new ChessPiece(squareButton, row1, row2,
								// COLSTRING.indexOf(column2) + 1, COLSTRING.indexOf(column2) + 1);

	@Override
	public void run() {

		// JFrame setup
		JFrame frame = new JFrame("Not Chess");
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(600, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// outer panel
		JPanel panel = new JPanel(new BorderLayout());
		frame.add(panel);

		// top panel for moves
		JPanel movePanel = new JPanel(new FlowLayout());
		panel.add(movePanel, BorderLayout.NORTH);

		moveLabel = new JLabel("Move:");
		turnLabel = new JLabel("White Turn");
		movePanel.add(moveLabel);
		movePanel.add(turnLabel);
		Font currFont = moveLabel.getFont();
		Font newFont = new Font(currFont.getFontName(), currFont.getStyle(), 12);
		moveLabel.setFont(newFont);

		// centering the move label and placing it in the middle of the panel
		moveLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.add(moveLabel, BorderLayout.NORTH);

		// panel for the grid of buttons, including the row/column headings
		JPanel gridPanel = new JPanel(new GridLayout(9, 9));
		JLabel[] colLabel = new JLabel[9];
		JLabel[] rowLabel = new JLabel[9];

		// display the column labels
		for (int col = 0; col < 9; col++) {
			colLabel[col] = new JLabel(COLUMN[col], JLabel.CENTER);
			gridPanel.add(colLabel[col]);
		}

		// itterate over the rows
		for (int row = 8; row > 0; row--) {
			gridPanel.add(new JLabel(" "));

			// add the row label
			rowLabel[row] = new JLabel(Integer.toString(row), JLabel.CENTER);
			gridPanel.add(rowLabel[row]);

			// itterate over the columns and add the buttons
			for (int col = 1; col < 9; col++) {
				squareButton[row][col] = new myButton(row, col, null);
				//ChessPiece p = new ChessPiece();
				squareButton[row][col].addActionListener(this);
				gridPanel.add(squareButton[row][col]);

			}
		}

		// put the pieces in their original position in the board
		for (int i = 1; i < 9; i++) {
			squareButton[2][i].setText("P");
			Pawn creation = new Pawn('P', Color.WHITE, i, 2, 0, 0);
			squareButton[2][i].curr = creation;
			pWhite.add(creation);
		}
		for (int i = 1; i < 9; i++) {
			squareButton[7][i].setText("P");
			Pawn creation = new Pawn('P', Color.BLACK, i, 7, 0, 0);
			squareButton[7][i].curr = creation;
			pBlack.add(creation);
		}
		for (int i = 1; i <= 8; i++) {
			squareButton[8][i].setText(PIECES[i]);
			squareButton[1][i].setText(PIECES[i]);
			char newPiece = PIECES[i].charAt(0);
			ChessPiece creation = null;
			ChessPiece creationTwo = null;
			switch (newPiece) {
				case 'R':
					creation = new Rook('R', Color.BLACK, i, 8, 0, 0);
					squareButton[8][i].curr = creation;
					pBlack.add(creation);

					creationTwo = new Rook('R', Color.WHITE, i, 1, 0, 0);
					squareButton[1][i].curr = creationTwo;
					pWhite.add(creation);
					break;
				case 'N':
					creation = new Knight('N', Color.BLACK, i, 8, 0, 0);
					squareButton[8][i].curr = creation;
					pBlack.add(creation);
					creationTwo = new Knight('N', Color.WHITE, i, 1, 0, 0);
					squareButton[1][i].curr = creationTwo;
					pWhite.add(creation);
					break;
				case 'K':
					creation = new King('K', Color.BLACK, i, 8, 0, 0);
					squareButton[8][i].curr = creation;
					pBlack.add(creation);
					creationTwo = new King('K', Color.WHITE, i, 1, 0, 0);
					squareButton[1][i].curr = creationTwo;
					pWhite.add(creation);
					break;
				case 'Q':
					creation = new Queen('Q', Color.BLACK, i, 8, 0, 0);
					squareButton[8][i].curr = creation;
					pBlack.add(creation);
					creationTwo = new Queen('Q', Color.WHITE, i, 1, 0, 0);
					squareButton[1][i].curr = creationTwo;
					pWhite.add(creation);
					break;
				case 'B':
					creation = new Bishop('B', Color.BLACK, i, 8, 0, 0);
					squareButton[8][i].curr = creation;
					pBlack.add(creation);
					creationTwo = new Bishop('B', Color.WHITE, i, 1, 0, 0);
					squareButton[1][i].curr = creationTwo;
					pWhite.add(creation);
					break;
			}

		}
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				if ((i + j) % 2 == 0) {
					squareButton[i][j].setBackground(Color.LIGHT_GRAY);
				} else {
					squareButton[i][j].setBackground(new Color(136, 136, 136));
				}
			}
		}
		// System.out.print(pBlack + " " + pWhite);
		// Set the two Colors for the top and bottom pieces
		for (int i = 1; i < 9; i++) {
			squareButton[1][i].setForeground(Color.white);
			squareButton[2][i].setForeground(Color.white);
		}
		// Add the buttons and their labels to the panel
		panel.add(gridPanel, BorderLayout.CENTER);
		// new panel for typing in a move.
		JPanel textPanel = new JPanel();
		frame.add(textPanel, BorderLayout.PAGE_END);
		// label for text box to type in a move
		JLabel enterLabel = new JLabel("Enter Move:");
		textPanel.add(enterLabel);
		// text box for typing in a move
		inputBox = new JTextField(5);
		textPanel.add(inputBox);
		// button to accept the typed move
		moveButton = new JButton("Move");
		moveButton.addActionListener(this);
		textPanel.add(moveButton);
		// display everything
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Check to see if what they typed is valid
		if (e.getSource() == moveButton) {
			textMove = inputBox.getText().toUpperCase();
			if (checkAndMove(inputBox.getText().toUpperCase())) {
				moveLabel.setText("Move: " + textMove.toUpperCase());
			} else {
				moveLabel.setText("Move: INVALID MOVE!");
			}
		}
		// otherwise, look at the mouse clicks
		else {
			if (firstPress) {
				firstPress = false;

				// cast the getSource object as myButton so we can then just
				// retrieve the row and column
				myButton aButton = (myButton) e.getSource();

				// check to see if the first click has a piece

				if (aButton.getText().equals("K") || aButton.getText().equals("N") || aButton.getText().equals("Q")
						|| aButton.getText().equals("B") || aButton.getText().equals("R")
						|| aButton.getText().equals("P")) {
					// start building the move as if it were typed
					buttonMove = aButton.getText() + COLUMN[aButton.getCol()] + aButton.getRow();
					myButtonReference = aButton.getForeground();
				} else {
					// button did not have a piece
					firstPress = true;
					moveLabel.setText("Move: INVALID MOVE!");
				}
			} else {
				// second click
				// as above, cast the getSource object as myButton so we can then
				// just retrieve the row and column
				myButton aButton = (myButton) e.getSource();

				// add this press to the string
				buttonMove = buttonMove + aButton.getText() + COLUMN[aButton.getCol()]
						+ aButton.getRow();

				if (checkAndMove(buttonMove)) {
					moveLabel.setText("Move: " + buttonMove);
				} else {
					moveLabel.setText("Move: INVALID MOVE!");
				}
			}

		}
	}

	public boolean capture(Color color) {
		if (color == Color.BLACK) {
			for (ChessPiece p : pBlack) {
				int x = p.x;
				int y = p.y;
				
				for (int x1 = 1; x1 <= 8; x1++) {
					for (int y1 = 1; y1 <= 8; y1++) {
						//p.validMove(squareButton, x, y, x1, y1);
						if (p.validMove(squareButton, x, y, x1, y1)) {
							blackMoves.add(p);
							 blackTurn = true;
							 whiteTurn = false;
						}
						else{
							whiteMoves.add(p);
							blackTurn=false;
							 whiteTurn = true;
						}
					}
				}
			}
		}
		else {
			for (ChessPiece p : pWhite) {
				int x = p.x;
				int y = p.y;

				System.out.println("Piece " + x + " " + y);
				for (int x1 = 1; x1 <= 8; x1++) {
					for (int y1 = 1; y1 <= 8; y1++) {
						//p.validMove(squareButton, x, y, x1, y1);
						if (p.validMove(squareButton, x, y, x1, y1)) {
							whiteMoves.add(p);
							 whiteTurn = true;
							 blackTurn = false;
						}
						else{
							blackMoves.add(p);
							blackTurn=true;
							 whiteTurn = false;
						}
					}
				}
			}
		}
		
		return true;

	}

	/**
	 * Returns whether the move is valid.
	 * If it is valid, moves the piece
	 * 
	 * @param the text string with the move
	 * 
	 * @return whether the move is valid.
	 */
	public Boolean checkAndMove(String textMove) {
		// assume that the move is valid
		Boolean isValid = true;
		// reset as if nothing has been pressed
		firstPress = true;

		// make sure that we have five characters
		if (textMove.length() != 5) {
			isValid = false;
			return isValid;
		}
		// break out each of the five characters
		String piece = textMove.substring(0, 1);
		String column1 = textMove.substring(1, 2);
		// make sure to catch non-numerics
		try {
			row1 = Integer.parseInt(textMove.substring(2, 3));
		} catch (NumberFormatException e) {
			isValid = false;
			return isValid;
		}
		String column2 = textMove.substring(3, 4);

		// make sure to catch non-numerics
		try {
			row2 = Integer.parseInt(textMove.substring(4, 5));
		} catch (NumberFormatException e) {
			isValid = false;
			return isValid;
		}

		// First, check to see if the string is formatted correctly
		// do we have a valid piece
		// if (!piece.equals("X") && !piece.equals("O")) {
		if (!piece.equals("K") && !piece.equals("Q") && !piece.equals("N") && !piece.equals("B") && !piece.equals("P")
				&& !piece.equals("R")) {
			isValid = false;
			return isValid;
		}

		// do we have a valid first column
		if (!COLSTRING.contains(column1)) {
			isValid = false;
			return isValid;
		}

		// do we have a valid first row
		if (row1 < 1 || row1 > 8) {
			isValid = false;
			return isValid;
		}

		// do we have a valid second column
		if (!COLSTRING.contains(column2)) {
			isValid = false;
			return isValid;
		}

		// do we have a valid second row
		if (row2 < 1 || row2 > 8) {
			isValid = false;
			return isValid;
		}

		// if we get this far we know that the string is formatted correctly
		// now we need to see if we can make this move
		// Does the initial space have the expected piece?
		if (!squareButton[row1][COLSTRING.indexOf(column1) + 1].getForeground().equals(myButtonReference)) {
			isValid = false;
			return isValid;
		}
		// Is the destination space empty?
		if (!squareButton[row2][COLSTRING.indexOf(column2) + 1].getText().equals("")) {
			isValid = false;
			return isValid;
		}

		if (myButtonReference == Color.WHITE && whiteTurn == false) {
			return false;
		}

		if (myButtonReference == Color.BLACK && blackTurn == false) {
			return false;
		}

		if (capture(myButtonReference)) {
			isValid = false;
		}
		//chess = new ChessPiece(0, myButtonReference, row1, row1, row1, row1);
		chess = squareButton[row1][COLSTRING.indexOf(column1)+1].curr;
		//System.out.println(squareButton[row1][COLSTRING.indexOf(column1) + 1].curr);
		if (chess.validMove(squareButton, row1, COLSTRING.indexOf(column1) +1,row2, COLSTRING.indexOf(column2)+1 ))
		// if (isValid) {
		{
			if(whiteTurn){
				whiteTurn= false;
				blackTurn=true;
				squareButton[row1][COLSTRING.indexOf(column1) + 1].setText("");
				squareButton[row2][COLSTRING.indexOf(column2) + 1].setText(piece);
				squareButton[row2][COLSTRING.indexOf(column2) + 1].setForeground(myButtonReference);
				squareButton[row2][COLSTRING.indexOf(column2) + 1].curr = squareButton[row1][COLSTRING.indexOf(column1) + 1].curr;
				squareButton[row1][COLSTRING.indexOf(column1) + 1].curr = null;
				turnLabel.setText("BLACK TURN");
				if(whiteTurn == false){
					isValid = false;
				}
				
			}	
			else if(blackTurn){
				
				blackTurn=false;
				whiteTurn=true;
				squareButton[row1][COLSTRING.indexOf(column1) + 1].setText("");
				squareButton[row2][COLSTRING.indexOf(column2) + 1].setText(piece);
				squareButton[row2][COLSTRING.indexOf(column2) + 1].setForeground(myButtonReference);
				squareButton[row2][COLSTRING.indexOf(column2) + 1].curr = squareButton[row1][COLSTRING.indexOf(column1) + 1].curr;
				squareButton[row1][COLSTRING.indexOf(column1) + 1].curr = null;
				turnLabel.setText("WHITE TURN");
				if(blackTurn == false){
					isValid = false;
				}
			}
			// squareButton[row1][COLSTRING.indexOf(column1) + 1].setText("");
			// squareButton[row2][COLSTRING.indexOf(column2) + 1].setText(piece);
			// squareButton[row2][COLSTRING.indexOf(column2) + 1].setForeground(myButtonReference);
			// squareButton[row2][COLSTRING.indexOf(column2) + 1].curr = squareButton[row1][COLSTRING.indexOf(column1) + 1].curr;
			// squareButton[row1][COLSTRING.indexOf(column1) + 1].curr = null;
	//	}
		 else {
			isValid = false;
			return isValid;
		}
		}
		if (isValid) {
			if (whiteTurn) {
				whiteTurn = false;
				blackTurn = true;
			}
			else {
				blackTurn = false;
				whiteTurn = true;
			}
		}
		return isValid;
	}

	public static void main(String args[]) {

		javax.swing.SwingUtilities.invokeLater(new notchess());
	}
}

// extending JButton to include the row and column
// adding adding these methods so we do not need to itterate
// over all of the buttons

class myButton extends JButton {
	protected int row;
	protected int col;
	protected ChessPiece curr;
	public Color color;

	public myButton(int row, int col, ChessPiece curr) {
		this.row = row;
		this.col = col;
		this.curr = curr;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public ChessPiece getCurr() {
		return curr;
	}

	public Color getColor() {
		return color;
	}
}