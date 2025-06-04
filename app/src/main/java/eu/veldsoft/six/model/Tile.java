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
	    NONE('E'),
	    
	    /**
	     *
	     */
	    BLACK('B'),

	    /**
	     *
	     */
	    RED('R');
	    
	/**
	*/
		private char symbol;
		
	/**
	*/
	    private Kind(char symbol) {
	    	this.symbol = symbol;
	    }
	    
	/**
	*/
	    char symbol() {
	    	return symbol;
	    }
	
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

