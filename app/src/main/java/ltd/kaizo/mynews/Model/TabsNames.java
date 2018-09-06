package ltd.kaizo.mynews.Model;

public enum TabsNames {
    TOP_STORIES(0),
    MOST_POPULAR(1),
    CUSTOM_TAB(2),
    SEARCH(3);

    private int id;

    TabsNames(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
