package com.david.test.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
//@WebFilter(filterName="crossOriginFilter",urlPatterns="/*")
public class CrossOriginControlFilter implements Filter{
    private boolean isCross = true;
    private Logger logger = Logger.getLogger(CrossOriginControlFilter.class);
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //检测跨域访问问题
        if(isCross){
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
//            System.out.println("拦截请求: "+httpServletRequest.getServletPath()+" session id: "+httpServletRequest.getSession().getId());
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "1800");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache,x-auth-token,withCredentials, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("XDomainRequestAllowed","1");
    
            logger.debug("拦截请求: "+httpServletRequest.getServletPath()+" session id: "+httpServletRequest.getSession().getId());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        isCross = false;
    }
}
