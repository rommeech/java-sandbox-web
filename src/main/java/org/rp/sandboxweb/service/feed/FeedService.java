package org.rp.sandboxweb.service.feed;

import org.rp.sandboxweb.dao.DAOException;
import org.rp.sandboxweb.dao.feed.FeedDAO;
import org.rp.sandboxweb.model.feed.Feed;

import java.util.List;

public class FeedService {

    public List<Feed> getAllFeeds() {
        List<Feed> feedList = null;
        FeedDAO dao = new FeedDAO();
        try {
            feedList = dao.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return feedList;
    }

    public Feed getFeed(Long id) {
        Feed feed = null;
        FeedDAO dao = new FeedDAO();
        try {
            feed = dao.getById(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return feed;
    }

}
