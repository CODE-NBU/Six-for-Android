package eu.veldsoft.six.model;

import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
class Player {
    /**
     * The name of the player.
     */
    private String name = "";

	/**
	 */
	private List<Tile> tiles = new ArrayList<>();

    /**
     * @param name
     */
    Player(String name) {
        super();
        this.name = name;
    }

	/**
	 */
	void reset() {
		tiles.clear();
	}
	
	/**
	 */
	void take(Tile tile) {
		tiles.add(tile);
	}
	
	/**
	 */
	Tile give() throws RuntimeException {
		if(tiles.size() <= 0) {
			throw new RuntimeException( "Tiles list is empty!" );
		}
		
		return tiles.remove( 0 );
	}

    /**
     * @return
     */
    String name() {
        return name;
    }
}
