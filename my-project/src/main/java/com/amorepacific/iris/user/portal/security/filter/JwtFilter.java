package com.amorepacific.iris.user.portal.security.filter;

import java.io.IOException;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amorepacific.iris.user.portal.common.constants.AuthConstants;
import com.amorepacific.iris.user.portal.common.util.JwtUtil;
import com.amorepacific.iris.user.portal.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(AuthConstants._AUTH_HEADER);

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(AuthConstants._TOKEN_TYPE)) {
            jwtToken = requestTokenHeader.substring(7);
            try {
            	if(!JwtUtil.isValidToken(jwtToken)) {
            		log.error("JWT is invalid");
            		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT is invalid");
            		return;
            	}
            	SecretKey secretKey = Keys.hmacShaKeyFor(JwtConfig.getSecretKey().getBytes());
            	Jws<Claims> claimsJws = Jwts.parserBuilder()
            			.setSigningKey(secretKey)
            			.build()
            			.parseClaimsJws(jwtToken);
            	Claims claims = claimsJws.getBody();
            	username = claims.getSubject();
            	
            } catch (Exception e) {
                log.error("JWT Token has expired or is invalid");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired or is invalid");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token does not begin with Bearer String");
        }

        // JWT 토큰을 통해 사용자명을 얻었으면, SecurityContext에 설정
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	log.info(username);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 토큰이 유효하면 수동으로 인증을 설정
            if (Jwts.parserBuilder().setSigningKey(JwtConfig.getSecretKey().getBytes()).build().isSigned(jwtToken)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
