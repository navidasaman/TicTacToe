import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

interface BoardInterface {
    JButton[] getCells(ActionListener listener);    
    JButton[] getGrid();
    JFrame getFrame();
    JPanel getCellPanel();
}

public class Board implements BoardInterface, ActionListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	JPanel contentPane;
	JPanel cellPanel;
	JPanel titlePanel;
	JButton[] cells;
	private JButton[] arrayOfAllGeneratedButtonsOnTheBoard = new JButton[9];
	
	// Singleton to ensure one single instance of Board
    private static Board instance = null;
    
    private Board(ActionListener listener) {
    	frame = getFrame();
		cellPanel = getCellPanel();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().add(cellPanel, BorderLayout.CENTER);
		cells = getCells(listener);
		System.out.println(cellPanel.getComponentCount());
		frame.setVisible(true);
    }
    
    public static Board getInstance(ActionListener listener) {  
	    if (instance == null){  
	    	instance = new Board(listener);
	    }  
	    return instance;  
    }
    
    public static void resetBoard() {
        instance = null;
    }
	
	// Getter to send all generated buttons to an array
	// which is to be used to be able to check for win
	public JButton[] getGrid() {
		return arrayOfAllGeneratedButtonsOnTheBoard;
	}

	// Create 9 JButtons and add to the cellPanel
	public JButton[] getCells(ActionListener listener) {
		for (int i = 0; i < 9; i++) {	
			JButton cellButton = new JButton();
			cellButton.addActionListener(listener);
			cellPanel.add(cellButton);
			
			// Get all the generated buttons and add each object to the array
			// to later be able to get those to use during win detection
			arrayOfAllGeneratedButtonsOnTheBoard[i] = cellButton;
		}
		return arrayOfAllGeneratedButtonsOnTheBoard;
	}

	// Create frame but don't display (since more components needs
	// to be added before the display)
	public JFrame getFrame() {
		frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(350, 50, 480, 585);
		frame.setBackground(Color.PINK);
		frame.setResizable(false);
		return frame;
	}

	// Create panel to hold all the cells
	public JPanel getCellPanel() {
		cellPanel = new JPanel();
		cellPanel.setLayout(new GridLayout(3, 3));
		return cellPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
