package eu.veldsoft.six.model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * This class mediates to the GUI.
 */
public class Board {
	private static class Node {
		static final int NE = 0;
		static final int E = 1;
		static final int SE = 2;
		static final int SW = 3;
		static final int W = 4;
		static final int NW = 5;
		Tile tile = null;
		Node links[] = {null, null, null, null, null, null, };
	}

    /**
     * List of the players around the board.
     */
    private List<Player> players = new ArrayList<>();

    /**
     *
     */
    private List<Tile> tiles = new ArrayList<>();

    /**
     * The player who is playing at the moment.
     */
    private Player playing = null;

    /**
     * Constructor without parameters.
     */
    public Board() {
        super();

        for(int i=0; i<21; i++) {
            tiles.add(new Tile(Tile.Kind.BLACK));
            tiles.add(new Tile(Tile.Kind.RED));
        }
    }
    
    /**
     */
    public boolean newGame(List<String> playersNames) {
    	if(playersNames.size() != 2) {
    		return false;
    	}
    	
    	players.clear();
	for (String name : playersNames) {
            players.add(new Player(name));
        }

        //TODO It is better each player to be able to be in each order.
        Collections.shuffle(players);

	/*
	 * Deal of tiles.
	 */
	for(int i=0; i<tiles.size(); i++) {
		if(i < tiles.size()/2) {
			players.get(0).take( tiles.get(i) );
		} else {
			players.get(1).take( tiles.get(i) );
		}
	}

	players.get(0).give();
	players.get(1).give();

        /*
         * The first player plays after start of the game.
         */
        playing = ((players.size() <= 0) ? null : players.get(0));
            	
    	return true;
    }
}

