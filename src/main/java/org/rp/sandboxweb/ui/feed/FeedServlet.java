package org.rp.sandboxweb.ui.feed;

import org.rp.sandboxweb.model.feed.Feed;
import org.rp.sandboxweb.service.FeedService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FeedServlet", urlPatterns = "/feeds")
public class FeedServlet extends HttpServlet {

    private FeedService feedService;

    @Override
    public void init() throws ServletException {
        super.init();
        feedService = new FeedService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Feed> feedList = feedService.getAllFeeds();
        request.setAttribute("feedList", feedList);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/feed/feeds.jsp");
        requestDispatcher.forward(request, response);
    }

    /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    } */
}
