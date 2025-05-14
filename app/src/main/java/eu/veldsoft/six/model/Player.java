package eu.veldsoft.six.model;

/**
 *
 */
class Player {
    /**
     * The name of the player.
     */
    private String name = "";

    /**
     * @param name
     */
    Player(String name) {
        super();
        this.name = name;
    }

    /**
     * @return
     */
    String name() {
        return name;
    }
}
