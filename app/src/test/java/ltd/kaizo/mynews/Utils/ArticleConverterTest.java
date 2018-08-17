package ltd.kaizo.mynews.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArticleConverterTest {

    @Test
    public void convertAStringToFormattedDate() {
        ArticleFormatter articleFormatter = new ArticleFormatter("test", "test", "test", "test", "2018-08-17T05:30:17-04:00");

        assertEquals("17/08/18", articleFormatter.getArticlePublishingDate());


    }

}