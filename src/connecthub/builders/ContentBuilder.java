/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub.builders;

import connecthub.entities.Content;
import connecthub.entities.ContentType;
import connecthub.entities.Post;
import connecthub.entities.Story;
import connecthub.interfaces.Builder;
import connecthub.mappers.ContentMapper;

/**
 *
 * @author User
 */
public class ContentBuilder implements Builder<Content> {

    private int authorId;
    private String content;
    private String imagePath;
    private static ContentBuilder instance;
    private ContentType type;

    // Private constructor to prevent instantiation
    private ContentBuilder() {
    }

    // Public method to get the single instance
    public static ContentBuilder getInstance() {
        if (instance == null) {
            instance = new ContentBuilder();
        }
        return instance;
    }

    public ContentBuilder setAuthorId(int authorId) {
        this.authorId = authorId;
        return this;
    }

    public ContentBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public ContentBuilder setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public ContentBuilder setContentType(ContentType type) {
        this.type = type;
        return this;
    }

    public ContentType getContentType() {
        return this.type;
    }

    @Override
    public Content build() {
        Content newContent;
        if (null == this.type) {
            newContent = new Post(authorId, content, imagePath);
        } else {
            switch (this.type) {
                case POST:
                    newContent = new Post(authorId, content, imagePath);
                    break;
                case STORY:
                    newContent = new Story(authorId, content, imagePath);
                    break;
                default:
                    newContent = new Post(authorId, content, imagePath);
                    break;
            }
        }
        ContentMapper.create(newContent);
        return newContent;
    }

}
