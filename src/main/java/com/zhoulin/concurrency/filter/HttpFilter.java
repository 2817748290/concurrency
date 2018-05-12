package com.zhoulin.concurrency.filter;

import com.zhoulin.concurrency.threadLocal.RequestHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 拦截器
 */
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        RequestHolder.add(Thread.currentThread().getId());

        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
