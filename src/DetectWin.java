import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DetectWin {
	// Used to stop game
	boolean gameOver = false;
	// message shown when game is over
	String outcomeMessage;
	TicTacToe ticTacToe;

	public void checkForWin(JButton buttonClicked, JButton[] arrayOfAllFetchedButtonsFromGrid) {
		checkWinColumns(buttonClicked, arrayOfAllFetchedButtonsFromGrid);
		checkWinRows(buttonClicked, arrayOfAllFetchedButtonsFromGrid);
		checkWinDiaglonals(buttonClicked, arrayOfAllFetchedButtonsFromGrid);

		// If it's a draw the game ends
		if (checkForDraw(arrayOfAllFetchedButtonsFromGrid)) {
			gameOver("It's a draw,");
		}
	}

	// Check if all the buttons have been given a value
	public boolean checkForDraw(JButton[] arrayOfAllFetchedButtonsFromGrid) {
		boolean notAllButtonsClicked = true;
		for (JButton button : arrayOfAllFetchedButtonsFromGrid) {
			// If the text of any button is empty
			// then then game resumes otherwise it's
			// a draw and the game ends
			if (button.getText().isEmpty()) {
				notAllButtonsClicked = false;
			}
		}
		return notAllButtonsClicked;
	}

	// Checks if there is a win on any of the rows
	private void checkWinRows(JButton buttonClicked, JButton[] arrayOfAllFetchedButtonsFromGrid) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (buttonClicked.getText().equals(arrayOfAllFetchedButtonsFromGrid[i].getText())
						&& buttonClicked.getText().equals(arrayOfAllFetchedButtonsFromGrid[i + 1].getText())
						&& buttonClicked.getText().equals(arrayOfAllFetchedButtonsFromGrid[i + 2].getText())) 
				{
					changeBackgroundColor(arrayOfAllFetchedButtonsFromGrid[i], arrayOfAllFetchedButtonsFromGrid[i + 1],
							arrayOfAllFetchedButtonsFromGrid[i + 2]);
					gameOver(buttonClicked.getText() + " won!");
				}
				i = i + 3;
			}
		}
	}

	// Checks if there is a win on any of the columns
	private void checkWinColumns(JButton buttonClicked, JButton[] arrayOfAllFetchedButtonsFromGrid) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (buttonClicked.getText().equals(arrayOfAllFetchedButtonsFromGrid[i].getText())
						&& buttonClicked.getText().equals(arrayOfAllFetchedButtonsFromGrid[i + 3].getText())
						&& buttonClicked.getText().equals(arrayOfAllFetchedButtonsFromGrid[i + 6].getText())) 
				{
					changeBackgroundColor(arrayOfAllFetchedButtonsFromGrid[i], arrayOfAllFetchedButtonsFromGrid[i + 3],
							arrayOfAllFetchedButtonsFromGrid[i + 6]);
					gameOver(buttonClicked.getText() + " won!");
				}
				i = i + 1;
			}
		}
	}

	// Checks if there is a win on any of the diagonals
	private void checkWinDiaglonals(JButton buttonClicked, JButton[] arrayOfAllFetchedButtonsFromGrid) {
		/*
		 * "valueOfOneForSecondLoop" is used since a=0 and i=0 during the first loop and
		 * therefore a/a respectively i/i can not be used (0 can never be denominator)
		 * to get the value 1 during the second loop. At the end of the first loop,
		 * "valueOfOneForSecondLoop" is given the value 1.
		 */
		int valueOfOneForSecondLoop = 0;

		/*
		 * "positionOfClickedCell" is used as the start value of each calculation. The
		 * difference between "positionOfClickedCell" and "i" is that at the beginning
		 * of the second loop a=2 and i=3 because of the natural increment of "i" as a
		 * part of the for-loop functionality.
		 */
		int positionOfClickedCell = 0;

		/*
		 * i < 5 is used so that the loop will only run two times. At the start of the
		 * second loop i=2. At the end of the the second loop i=5.
		 */
		for (int i = 0; i < 5; i++) {

			/*
			 * The values inside the equals() during the first loop: (a)=**0**,
			 * (a+(4-i+(i/2)))=**4** and ((a+8-one)-i)=**8** The values inside the equals()
			 * during the second loop: (a)=**2**, (a+(4-i+(i/2)))=**4** and
			 * ((a+8-one)-i)=**6** The changing of values were made possible due to the
			 * start value 0, of "a" and "i", making the constants 4 and 8 the only values
			 * impacting the final value of the calculations during the first loop through.
			 */
			if (buttonClicked.getText().equals(arrayOfAllFetchedButtonsFromGrid[positionOfClickedCell].getText())
					&& buttonClicked.getText().equals(
							arrayOfAllFetchedButtonsFromGrid[positionOfClickedCell + (4 - i + (i / 2))].getText())
					&& buttonClicked.getText().equals(
							arrayOfAllFetchedButtonsFromGrid[(positionOfClickedCell + 8 - valueOfOneForSecondLoop) - i]
									.getText())) 
			{
				changeBackgroundColor(arrayOfAllFetchedButtonsFromGrid[positionOfClickedCell],
						arrayOfAllFetchedButtonsFromGrid[positionOfClickedCell + (4 - i + (i / 2))],
						arrayOfAllFetchedButtonsFromGrid[(positionOfClickedCell + 8 - valueOfOneForSecondLoop) - i]);
				gameOver(buttonClicked.getText() + " won!");
			}
			valueOfOneForSecondLoop = 1;
			positionOfClickedCell = positionOfClickedCell + 2;
			i = i + 2;
		}
	}

	// Background colour changed to emphasise the winning streak
	private void changeBackgroundColor(JButton winStreakButtonOne, JButton winStreakButtonTwo,
		JButton winStreakButtonThree) 
	{
		winStreakButtonOne.setBackground(Color.green);
		winStreakButtonTwo.setBackground(Color.green);
		winStreakButtonThree.setBackground(Color.green);
	}

	// Dialog box to end game
	private void gameOver(String outcomeMessage) {
		gameOver = true;
		JOptionPane playAgain = new JOptionPane();
		int yesOrNo = JOptionPane.showConfirmDialog(playAgain,
			outcomeMessage + " Changing start player, play again?", "Game Over",
			JOptionPane.YES_NO_OPTION);
		{
			if (yesOrNo == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else {
				// Disposes the frame
				Board.frame.dispose();
				// Sets the instance of Board to null
				Board.resetBoard();
				// Creates new TicTacToe round
				new TicTacToe();
			}
		}
	}
}
