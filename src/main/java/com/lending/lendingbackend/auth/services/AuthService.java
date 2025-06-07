package com.lending.lendingbackend.auth.services;


import com.lending.lendingbackend.auth.config.BCrypt.BCryptPasswordEncoder;
import com.lending.lendingbackend.auth.data.entity.session.Session;
import com.lending.lendingbackend.exceptions.data.UnregisteredUserException;
import com.lending.lendingbackend.auth.services.parser.AuthorizationHeaderToCredentialParser;
import com.lending.lendingbackend.auth.services.parser.CookieHeaderParser;
import com.lending.lendingbackend.auth.services.parser.Credential;
import com.lending.lendingbackend.data.entity.User;
import com.lending.lendingbackend.dto.LoginDTO;
import com.lending.lendingbackend.dto.RegistrationDTO;
import com.lending.lendingbackend.service.ClientService;
import com.lending.lendingbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {


    public static final String COOKIE_HEADER_SESSION_ID_NAME = "CATSSESSIONID";
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ClientService clientService;
    private final RedisSessionService redisSessionService;

    private static Credential authenticationHeaderParse(String authenticationHeader) {
        return AuthorizationHeaderToCredentialParser.parse(authenticationHeader);
    }

    public boolean signUp(RegistrationDTO registrationDTO) {
        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        clientService.createClientByRegistrationDTO(registrationDTO);
        return true;
    }


    public CustomResponse signIn(LoginDTO loginDTO) {
        User user = userService.findUserByLogin(loginDTO.getLogin());
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new UnregisteredUserException();
        }
        Session session = createUserSession(loginDTO.getLogin());
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCookieSessionId(session.getSessionId());
        customResponse.setUserId(redisSessionService.getUserIdFromSession(session.getSessionId()));
        return customResponse;
    }


    //TODO: снести после отладки
    public boolean logout(String cookieHeader) {
        String sessionId = CookieHeaderParser.getSessionIdCookie(cookieHeader, COOKIE_HEADER_SESSION_ID_NAME);
        RBucket<String> bucket = redisSessionService.getRBucket(sessionId);
        bucket.delete();
        return true;
    }

    public Long getUserIdFromCookie(String cookieHeader) {
        String sessionId = CookieHeaderParser.getSessionIdCookie(cookieHeader, COOKIE_HEADER_SESSION_ID_NAME);

        return redisSessionService.getUserIdFromSession(sessionId);
    }

    private Session createUserSession(String login) {
        String sessionId = UUID.randomUUID().toString();
        User user = userService.findUserByLogin(login);
        return createSession(sessionId, user.getId());

    }


    private Session createSession(String sessionId, long id) {
        Session session = new Session();
        session.setSessionId(sessionId);
        session.setUserId(id);
        session.setCreatedAt(LocalDateTime.now());
        session.setExpiresAt(LocalDateTime.now().plusHours(2));
        redisSessionService.saveSessionToRedis(session);
        return session;
    }


}
