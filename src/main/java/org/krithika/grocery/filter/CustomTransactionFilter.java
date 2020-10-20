package org.krithika.grocery.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by administrator on 20/10/20.
 */
public class CustomTransactionFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CustomTransactionFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("Starting a transaction for req : {}", req.getRequestURI());
        chain.doFilter(request, response);
        logger.info("Committing a transaction for req : {}", req.getRequestURI());
    }


}
