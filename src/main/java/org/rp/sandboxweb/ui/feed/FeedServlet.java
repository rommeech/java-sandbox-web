package org.rp.sandboxweb.ui.feed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rp.sandboxweb.model.feed.Feed;
import org.rp.sandboxweb.service.feed.FeedService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FeedServlet", urlPatterns = {"/feeds", "/feeds/*"})
public class FeedServlet extends HttpServlet {

    // TODO: bean?
    private FeedService feedService;
    private static Logger logger;

    @Override
    public void init() throws ServletException {
        super.init();
        feedService = new FeedService();
        logger = LogManager.getLogger(FeedServlet.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> pathSegments = (List<String>) request.getAttribute("pathSegments");

        if (pathSegments == null || pathSegments.size() == 0) {
            showFeedsList(request, response);
        }

        else if (pathSegments.size() > 1) {
            logger.error("Invalid URL " + request.getRequestURI());
            error404(request, response);
        }

        else {
            showFeedsForm(request, response, pathSegments.get(0));
        }
    }

    private void showFeedsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Feed> feedList = feedService.getAllFeeds();
        request.setAttribute("feedList", feedList);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/feed/feeds_list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showFeedsForm(HttpServletRequest request, HttpServletResponse response, String feedId) throws ServletException, IOException {
        Feed feed = feedService.getFeed(Long.parseLong(feedId));

        if (feed == null) {
            logger.error("Invalid FeedID=" + feedId);
            error404(request, response);
        }
        else {
            logger.debug("FeedID=" + feedId);
            request.setAttribute("feed", feed);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/feed/feeds_form.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void error404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: make it available for other servlets
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setContentType("text/html, charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<h1>404 Not Found</h1>");
    }



    /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    } */
}
