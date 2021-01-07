package travelling.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import travelling.api.app.common.constant.MessageConstant;
import travelling.api.app.model.response.GeneralResponse;
import travelling.api.app.model.response.ResponseStatus;
import travelling.api.app.translate.ResponseUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private final TokenAuthenticator authenticator;
    private ObjectMapper mapper;
    private ResponseUtils responseUtils;


    public AuthenticationFilter(TokenAuthenticator authenticator, ObjectMapper mapper, ResponseUtils responseUtils) {
        this.authenticator = authenticator;
        this.mapper = mapper;
        this.responseUtils = responseUtils;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        Authentication userAuth;
        String token = ((HttpServletRequest) req).getHeader(AUTH_HEADER_NAME);

        try {
            userAuth = this.authenticator.getAuthentication(token);
        } catch (InvalidJwtException e) {
            HttpServletResponse resp = (HttpServletResponse) res;
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            GeneralResponse err;
            if (e.getMessage().contains("no longer valid")) {
                err = responseUtils.generalResponse(MessageConstant.EXPIRED_TOKEN.value());
            } else {
                err = responseUtils.generalResponse(MessageConstant.INVALID_TOKEN.value());
            }

            this.mapper.writeValue(resp.getOutputStream(), err);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(userAuth);
        chain.doFilter(req, res);
    }

    private GeneralResponse unauthorizedResponse(ErrorCode error) {
        GeneralResponse err = new GeneralResponse();
        err.setStatus(new ResponseStatus(error.code(), error.message()));

        return err;
    }
}
