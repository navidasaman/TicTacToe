public class PieceTwo implements Piece {

	// Singleton
	private static PieceTwo instance = null;
	public String str;

	public char getPieceSymbol(char symbol) {
		return symbol;
	}

	private PieceTwo() {
	}

	public static PieceTwo getInstance() {
		if (instance == null) {
			instance = new PieceTwo();
		}
		return instance;
	}
}