package org.rp.sandboxweb.model.feed;

import org.rp.sandboxweb.model.AbstractModel;
import org.rp.sandboxweb.model.Status;

import java.sql.Date;
import java.util.Objects;

public class Feed extends AbstractModel<Long> {

    private static final long serialVersionUID = 2449154952818613827L;

    private String feedUrl;
    private String logoUrl;
    private String title;
    private String description;
    private Status status;
    private String author;
    private Date nextJob;
    private Long jobInterval;

    public Feed() {
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getNextJob() {
        return nextJob;
    }

    public void setNextJob(Date nextJob) {
        this.nextJob = nextJob;
    }

    public Long getJobInterval() {
        return jobInterval;
    }

    public void setJobInterval(Long jobInterval) {
        this.jobInterval = jobInterval;
    }

    @Override
    public String toString() {
        return "Feed{id=" + getId() +
                ", feedUrl='" + feedUrl + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", author='" + author + '\'' +
                ", nextJob=" + nextJob +
                ", jobInterval=" + jobInterval +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feed)) return false;
        if (!super.equals(o)) return false;
        Feed feed = (Feed) o;
        return Objects.equals(feedUrl, feed.feedUrl) &&
                Objects.equals(logoUrl, feed.logoUrl) &&
                Objects.equals(title, feed.title) &&
                Objects.equals(description, feed.description) &&
                Objects.equals(status, feed.status) &&
                Objects.equals(author, feed.author) &&
                Objects.equals(nextJob, feed.nextJob) &&
                Objects.equals(jobInterval, feed.jobInterval);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), feedUrl, logoUrl, title, description, status, author, nextJob, jobInterval);
    }
}
