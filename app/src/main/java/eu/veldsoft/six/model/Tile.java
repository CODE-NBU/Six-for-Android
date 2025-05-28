package eu.veldsoft.six.model;

/**
 *
 */
class Tile {
	/**
	 */
	static enum Kind {
	    /**
	     *
	     */
	    BLACK,

	    /**
	     *
	     */
	    RED,
	}
	
	/**
	 */
	private Kind kind = null;

	/**
	 */	
	Tile(Kind kind) {
		super();
		
		this.kind = kind;
	}
	
	/**
	 */
	Kind kind() {
		return kind;
	}
}

