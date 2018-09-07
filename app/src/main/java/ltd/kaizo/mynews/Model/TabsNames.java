package ltd.kaizo.mynews.Model;

/**
 * The enum Tabs names.
 */
public enum TabsNames {
    /**
     * Top stories tabs names.
     */
    TOP_STORIES(0),
    /**
     * Most popular tabs names.
     */
    MOST_POPULAR(1),
    /**
     * Custom tab tabs names.
     */
    CUSTOM_TAB(2),
    /**
     * Search tabs names.
     */
    SEARCH(3);

    /**
     * The Id.
     */
    private int id;

    /**
     * Instantiates a new Tabs names.
     *
     * @param id the id
     */
    TabsNames(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
}
