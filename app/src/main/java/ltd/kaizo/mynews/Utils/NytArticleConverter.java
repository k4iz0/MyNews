package ltd.kaizo.mynews.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesAPIData;
import ltd.kaizo.mynews.Model.NytTopStoriesAPI.NytTopStoriesResult;

public class NytArticleConverter {
    private List<NytTopStoriesResult> nytArticleList;
//TODO générifier les recycleview
    public NytArticleConverter(NytTopStoriesAPIData nytTopStoriesAPIData) {
       this.setNytArticleList(nytTopStoriesAPIData);
    }



    public List<NytTopStoriesResult> getNytArticleList() {
        return nytArticleList;
    }

    public void setNytArticleList(NytTopStoriesAPIData nytTopStoriesAPIData) {
        this.nytArticleList = new ArrayList<>();
        this.nytArticleList.addAll(Arrays.asList(nytTopStoriesAPIData.getResults()));
    }

}
