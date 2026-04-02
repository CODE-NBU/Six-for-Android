package eu.veldsoft.six.model;

/**
 * Represents a tile on the board.
 */
final class Tile {
	/**
	 * Types of tiles.
	 */
	static enum Kind {
		/**
		 * Represents an empty tile.
		 */
		NONE('E'),

		/**
		 * Represents a black tile.
		 */
		BLACK('B'),

		/**
		 * Represents a red tile.
		 */
		RED('R');

		/**
		 * The symbol representing the tile.
		 */
		private char symbol;

		/**
		 * Initializes the tile kind with the given symbol.
		 * @param symbol The symbol representing the tile.
		 */
		private Kind(char symbol) {
			this.symbol = symbol;
		}

		/**
		 * Returns the symbol representing the tile.
		 * @return The symbol representing the tile.
		 */
		char symbol() {
			return symbol;
		}
	}

	/**
	 * The kind of the tile.
	 */
	private final Kind kind;

	/**
	 * Initializes the tile with the given kind.
	 * @param kind The kind of the tile.
	 */
	Tile(Kind kind) {
		super();

		this.kind = kind;
	}

	/**
	 * Returns the kind of the tile.
	 * @return The kind of the tile.
	 */
	Kind kind() {
		return kind;
	}
}

