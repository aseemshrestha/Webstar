package com.webstar.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@WebFilter(urlPatterns={"/*"})
@Component 
public class XssFilter implements Filter
{
    private static final Logger logger = Logger.getLogger(XssFilter.class);
   
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
       logger.info("{XSSFilter]-init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        chain.doFilter(new XssRequestWrapper((HttpServletRequest)request), response);
    }

    @Override
    public void destroy()
    {
        logger.info("{XSSFilter]-destroy");
    }

}
