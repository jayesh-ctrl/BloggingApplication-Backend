package com.blog.security;

import java.io.IOException;

import com.blog.exceptions.APIException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        // 1. get token
        String requestToken = request.getHeader("Authorization");
        // Bearer mrmfmrrf
        System.out.println(requestToken);
        String username = null;
        String token = null;
        if(requestToken!=null && requestToken.startsWith("Bearer")) {
             token = requestToken.substring(7);
             try {
                 username = this.jwtTokenHelper.getUsernameFromToken(token);
             } catch (IllegalArgumentException ex) {
                 throw new APIException("unable to get username");
             } catch (ExpiredJwtException ex) {
                 throw new APIException("JWT Token has expired !!!");
             } catch (MalformedJwtException ex) {
                 throw new APIException("Invalid JWT Exception ");
             }
        } else {
            System.out.println("JWT Does not start with Bearer");
//            throw new APIException("JWT does not start with Bearer");
        }
        // once we get the token, now validation of token.
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token,userDetails)) {
                // sahi chal rha hai authentication krna hai
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                throw new APIException("Invalid JWT Token !!!");
            }

        } else {
            System.out.println("username is null or context is not null");
//            throw new APIException("username is null or context is not null");
        }
        filterChain.doFilter(request,response);
    }
}
