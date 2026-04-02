package eu.veldsoft.six.model;

import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game.
 */
final class Player {
	/**
	 * The name of the player.
	 */
	private String name = "";

	/**
	 * The list of tiles the player has.
	 */
	private List<Tile> tiles = new ArrayList<>();

	/**
	 * Constructor.
	 * @param name The name of the player.
	 */
	Player(String name) {
		super();
		this.name = name;
	}

	/**
	 * Resets the player's state.
	 */
	void reset() {
		tiles.clear();
	}

	/**
	 * Adds a tile to the player's list of tiles.
	 * @param tile The tile to take.
	 */
	void take(Tile tile) {
		tiles.add(tile);
	}

	/**
	 * Removes and returns the first tile from the player's list of tiles.
	 * @return The tile that was removed.
	 * @throws RuntimeException If the tiles list is empty.
	 */
	Tile give() throws RuntimeException {
		if(tiles.size() <= 0) {
			throw new RuntimeException( "Tiles list is empty!" );
		}

		return tiles.remove( 0 );
	}

	/**
	 * Returns the name of the player.
	 * @return The name of the player.
	 */
	String name() {
		return name;
	}

	/**
	 * Returns a string representation of the player.
	 * @return A string representation of the player.
	 */
	@Override
	public String toString() {
		return "Player: " + name;
	}
}
