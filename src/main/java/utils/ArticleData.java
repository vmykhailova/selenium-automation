package utils;

import model.Article;

public class ArticleData {

    public static Article createArticle(){
        return new Article("Create Test Article", "It's new created Article for Test", "Create Text text text text text text", "Create Tag Article");
    }

}
