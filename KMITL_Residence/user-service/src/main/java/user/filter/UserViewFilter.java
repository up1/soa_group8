package user.filter;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import user.exception.UnauthorizedException;
import user.jwt.JwtService;
import user.model.JwtUser;
import user.model.User;
import user.repository.UserRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Adisorn on 2/4/2560.
 */
@WebFilter(urlPatterns = { "/user/*" })
public class UserViewFilter implements Filter {

    @Value("${authentication.token.header}")
    String authHeader;

    @Autowired
    private JwtService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

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
                String[] url = httpRequest.getRequestURL().toString().split("/");
                String username = url[url.length-1];
                User userInformation = userRepository.getUser(username);
                if(!user.getUsername().equals(userInformation.getUsername()) && !user.getRole().equals("Admin")) {
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
