package org.rp.sandboxweb.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UrlParserFilter implements Filter {

    private static Logger logger;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger = LogManager.getLogger(UrlParserFilter.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        List<String> pathSegments = null;

        if (request instanceof HttpServletRequest) {
            String pathInfo = ((HttpServletRequest)request).getPathInfo();
            if (pathInfo != null && !pathInfo.equals("") && !pathInfo.equals("/")) {
                pathInfo = pathInfo.replaceAll("^/", "");
                pathInfo = pathInfo.replaceAll("/$", "");
                pathSegments = Arrays.asList(pathInfo.split("/"));
            }
            request.setAttribute("pathSegments", pathSegments);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
