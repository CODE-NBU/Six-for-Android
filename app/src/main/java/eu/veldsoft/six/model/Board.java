package eu.veldsoft.six.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class mediates to the GUI.
 */
public class Board {
    /**
     * List of the players around the board.
     */
    private List<Player> players = new ArrayList<>();

    /**
     *
     */
    private List<Tile> tiles = new ArrayList<>();

    /**
     * Constructor without parameters.
     */
    public Board() {
        super();

        for(int i=0; i<21; i++) {
            tiles.add(Tile.BLACK);
            tiles.add(Tile.RED);
        }
    }
}
