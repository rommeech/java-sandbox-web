package org.rp.sandboxweb.model.feed;

import org.rp.sandboxweb.model.AbstractModel;

import java.util.Objects;

public class Post extends AbstractModel<Long> {

    private static final long serialVersionUID = 8000149224875624749L;

    private Feed feed;
    private String xid;
    private String title;
    private String content;
    private String author;
    private String url;

    public Post() {
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PostDAO{id=" + getId() +
                ", feed=" + feed +
                ", xid='" + xid + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equals(feed, post.feed) &&
                Objects.equals(xid, post.xid) &&
                Objects.equals(title, post.title) &&
                Objects.equals(content, post.content) &&
                Objects.equals(author, post.author) &&
                Objects.equals(url, post.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), feed, xid, title, content, author, url);
    }
}
