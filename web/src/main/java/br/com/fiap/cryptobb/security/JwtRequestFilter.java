package br.com.fiap.cryptobb.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String BEARER = "Bearer ";
    private JwtTokenUtils tokenUtils;
    private JwtUserDetailsService jwtUserDetailsService;
    private Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    public JwtRequestFilter(JwtTokenUtils tokenUtils,
                            JwtUserDetailsService jwtUserDetailsService){
        this.tokenUtils = tokenUtils;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION); // Token com prefixo Bearer
        String username = null;
        String token;

        if(headerToken != null && headerToken.startsWith(BEARER)){
            token = headerToken.replace(BEARER, "");
            try{
                username = tokenUtils.getUsernameFromToken(token);
            } catch (IllegalArgumentException illegalArgumentException){
                logger.info("Token invalid");
            }catch (ExpiredJwtException expiredJwtException){
                logger.info("Token expired");
            }

        } else {
            logger.info("Token unavailable or invalid");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                    userDetails,null, userDetails.getAuthorities()
            );

            userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(userToken);
        }

        filterChain.doFilter(request, response);
    }
}
