package com.company.security.config;

import java.io.IOException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.common.exception.ErrorResponse;
import com.company.common.exception.SecurityUtils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private TokenProvider jwtTokenProvider;

	private UserDetailsService userDetailsService;

	// Constructor
	public JwtAuthenticationFilter(TokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetailsService = userDetailsService;
	}

	// This method is executed for every request intercepted by the filter.
	// And, it extract the token from the request header and validate the token.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Get JWT token from HTTP request
		String token = getTokenFromRequest(request);
		log.info("token : " + token);
		try {
			// Validate Token
			if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
				// get username from token
				String username = jwtTokenProvider.getUsername(token);
				log.info("username : " + username);

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				log.info("userDetails : " + userDetails);

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			filterChain.doFilter(request, response);
		} catch (SignatureException e) {
			log.error("Error on method [doFilterInternal] Exception: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (JwtException e) {
			log.error("Error on method [doFilterInternal] Exception: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		}
	}

	// Extract the token
	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}

		return null;
	}
}