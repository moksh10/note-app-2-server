package com.project.noteappmain.filters;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.noteappmain.handlers.AuthorizationException;
import com.project.noteappmain.services.UserDetailsService;
import com.project.noteappmain.services.UserServiceImpl;
import com.project.noteappmain.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	
    	if(request.getRequestURI().toString().equals("/authFailure"))
    	{
    		chain.doFilter(request, response);
    		return;
    	}

    	Cookie[] cookies= request.getCookies();
    	String authorization = null;
    	if(cookies!=null)
    	{
    		for(Cookie cookie:cookies)
        	{
        		if(cookie.getName().equals("jwt"))
        		{
        			authorization=cookie.getValue();
        		}
        	}
    	}
    	
        
        String username = null;
        String jwt = null;

        if (authorization != null ) {
            jwt = authorization;
            try {
            	username = jwtUtil.extractUsername(jwt);
                
			} catch (Exception e) {
			}
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                int userId=userServiceImpl.getUserByEmail(username).getUserId();
                request.setAttribute("userId",userId);
            }
            else {
            	throw new AuthorizationException();
    		}
            
            
        }
        
        chain.doFilter(request, response);
    }

}