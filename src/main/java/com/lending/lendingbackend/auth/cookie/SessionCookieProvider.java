package com.lending.lendingbackend.auth.cookie;

import com.lending.lendingbackend.auth.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

public class SessionCookieProvider {
    public static void setUpClientSessionCookie(HttpServletResponse response, String sessionId) {
        ResponseCookie cookie = ResponseCookie.from(AuthService.COOKIE_HEADER_SESSION_ID_NAME, sessionId)
                .httpOnly(true)
                .secure(false) // В development можно false, в production — true
                .path("/client")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Lax") // Или "None" + Secure=true для кросс-сайта
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public static void setUpManagerSessionCookie(HttpServletResponse response, String sessionId) {
        ResponseCookie cookie = ResponseCookie.from(AuthService.COOKIE_HEADER_SESSION_ID_NAME, sessionId)
                .httpOnly(true)
                .secure(false) // В development можно false, в production — true
                .path("/manager")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Lax") // Или "None" + Secure=true для кросс-сайта
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public static void setUpAdminSessionCookie(HttpServletResponse response, String sessionId) {
        ResponseCookie cookie = ResponseCookie.from(AuthService.COOKIE_HEADER_SESSION_ID_NAME, sessionId)
                .httpOnly(true)
                .secure(false) // В development можно false, в production — true
                .path("/admin")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Lax") // Или "None" + Secure=true для кросс-сайта
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
}
