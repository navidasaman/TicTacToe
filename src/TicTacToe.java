import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class TicTacToe implements ActionListener {
	
	public static void main(String[] args) {
		new TicTacToe();
	}
	
	private DetectWin detectWin;
	private Board gameBoard;
	private JButton buttonClicked;

	// First piece to be played
	private static char pieceSymbol = 'O';
	
	// Instantiating pieces to be used during game
	private Piece pieceOne = PieceOne.getInstance();
    private Piece pieceTwo = PieceTwo.getInstance();
	
	
	// Declaring array to hold the created buttons from the grid
	private JButton[] arrayOfAllFetchedButtonsFromGrid;
	
	public TicTacToe() {
		detectWin = new DetectWin();
		gameBoard = Board.getInstance(this);
		arrayOfAllFetchedButtonsFromGrid = gameBoard.getGrid();
	}

	// ActionListener saying what'll happen when a button is clicked
	public void actionPerformed(ActionEvent e) {
		buttonClicked = (JButton) e.getSource();
		buttonClicked.setText(String.valueOf(pieceSymbol));

		// Switching the piece to be played
		// If the piece played was X, then switch to O
		if (pieceSymbol == pieceTwo.getPieceSymbol('X')) {
			pieceSymbol = pieceOne.getPieceSymbol('O');
		}
		// If the piece played wasn't X (is O), then piece X should be played next
		else {
			pieceSymbol = pieceTwo.getPieceSymbol('X');
		}
		
		// Check if the added piece resulted in a win
		detectWin.checkForWin(buttonClicked, arrayOfAllFetchedButtonsFromGrid);
		
		// Remove the ActionListener from the pressed button
		buttonClicked.removeActionListener(this);
	}
}
