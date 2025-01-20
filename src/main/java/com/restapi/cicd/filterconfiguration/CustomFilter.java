package com.restapi.cicd.filterconfiguration;


import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.logging.LogRecord;

public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("******** processing filter "+request.getMethod()+ " "+ request.getRequestURI());
        ContentCachingResponseWrapper wrappedResponse  = new ContentCachingResponseWrapper(response);
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        try {
            filterChain.doFilter(wrappedRequest,wrappedResponse);
            String responseBody = new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());
            String requestBody = new String(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding());
            System.out.println("request: {}" + requestBody.replaceAll("\\s+", " "));
            System.out.println("response:{}" + responseBody);
        } finally {
            wrappedResponse.copyBodyToResponse();
        }
    }

    @Override
    public void destroy() {

    }

    private void logRequestDetails(HttpServletRequest request){
        System.out.println("Method: " + request.getMethod());
        System.out.println("Headers:");
        request.getHeaderNames().asIterator().forEachRemaining(header ->
                System.out.println(header + ": " + request.getHeader(header))
        );
    }
}
