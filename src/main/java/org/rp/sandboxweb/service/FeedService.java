package org.rp.sandboxweb.service;

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

}
