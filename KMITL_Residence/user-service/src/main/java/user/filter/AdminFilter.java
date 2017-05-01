package user.filter;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import user.exception.UnauthorizedException;
import user.jwt.JwtService;
import user.model.JwtUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Adisorn on 2/4/2560.
 */
@WebFilter(urlPatterns = { "/users/create", "/users", "/users/" })
public class AdminFilter implements Filter {

    @Value("${authentication.token.header}")
    String authHeader;

    @Autowired
    private JwtService jwtTokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        final String authHeaderVal = httpRequest.getHeader(authHeader);
        if(authHeaderVal == null) {
            throw new UnauthorizedException();
        }
        else {
            try {
                JwtUser user = jwtTokenService.getUser(authHeaderVal);
                if(!user.getRole().equals("Admin")) {
                    throw new UnauthorizedException();
                }
            }catch (JwtException ex) {
                ex.printStackTrace();
                throw new UnauthorizedException();
            }
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
