package org.rp.sandboxweb.ui;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UrlParserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        List<String> pathSegments = null;

        if (request instanceof HttpServletRequest) {
            String pathInfo = ((HttpServletRequest)request).getPathInfo();
            if (pathInfo != null) {
                pathInfo = pathInfo.replaceAll("^/", "");
                pathInfo = pathInfo.replaceAll("/$", "");
                if (pathInfo != "") {
                    pathSegments = Arrays.asList(pathInfo.split("/"));
                }
            }
            request.setAttribute("pathSegments", pathSegments);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
