package com.lending.lendingbackend.auth.filter;

import com.lending.lendingbackend.exceptions.auth.InvalidCookieException;
import com.lending.lendingbackend.auth.httpresponse.HttpResponse;
import com.lending.lendingbackend.auth.services.AuthService;
import com.lending.lendingbackend.auth.services.RedisSessionService;
import com.lending.lendingbackend.auth.services.parser.CookieHeaderParser;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;


@RequiredArgsConstructor
public class SessionExistFilter implements Filter {

    private final RedisSessionService redisSessionService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest httpRequest) || !(response instanceof HttpServletResponse httpResponse)) {
            chain.doFilter(request, response);
            return;
        }
        String cookie = httpRequest.getHeader("Cookie");

        if (cookie == null) {
            throw new InvalidCookieException();
        }
        String sessionId = CookieHeaderParser.getSessionIdCookie(cookie, AuthService.COOKIE_HEADER_SESSION_ID_NAME);
        if (sessionId == null || sessionId.isEmpty()) {
            httpResponse = HttpResponse.UNAUTHORIZED.getResponse(httpResponse);
            return;
        }

        if (redisSessionService.isEmptySessionRBucket(sessionId)) {
            httpResponse = HttpResponse.UNAUTHORIZED.getResponse(httpResponse);
            return;
        }
        chain.doFilter(request, response);
    }
}
