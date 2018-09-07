package ltd.kaizo.mynews.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArticleFormatterTest {
    @Test
    public void giveAStringShouldReturnAFormattedOne() {
        ArticleFormatter articleFormatter = new ArticleFormatter("Test article", "http://foo.com", "", "test", "", "2018-08-30");
        String dateFormatted = articleFormatter.getArticlePublishingDate();
        assertEquals("30/08/18", dateFormatted);

    }
}