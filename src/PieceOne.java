public class PieceOne implements Piece {
	// Singleton
	private static PieceOne instance = null;
	public String str;

	public char getPieceSymbol(char symbol) {
		return symbol;
	}

	private PieceOne() {
	}

	public static PieceOne getInstance() {
		if (instance == null) {
			instance = new PieceOne();
		}
		return instance;
	}

}