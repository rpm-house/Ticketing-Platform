package com.company.common.exception;

import java.io.IOException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (SignatureException e) {
			log.error("Error on method [doFilterInternal] SignatureException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (JwtException e) {
			log.error("Error on method [doFilterInternal] JwtException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (AuthorizationDeniedException e) {
			log.error("Error on method [doFilterInternal] AuthorizationDeniedException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (DataIntegrityViolationException e) {
			log.error("Error on method [doFilterInternal] DataIntegrityViolationException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (EntityNotFoundException e) {
			log.error("Error on method [doFilterInternal] EntityNotFoundException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (ConstraintViolationException e) {
			log.error("Error on method [doFilterInternal] ConstraintViolationException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		}catch (HttpRequestMethodNotSupportedException e) {
			log.error("Error on method [doFilterInternal] HttpRequestMethodNotSupportedException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		}catch (TicketException e) {
			log.error("Error on method [doFilterInternal] TicketException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (RuntimeException e) {
			log.error("Error on method [doFilterInternal] RuntimeException: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		} catch (Exception e) {
			log.error("Error on method [doFilterInternal] Exception: {}", e.getMessage());
			ErrorResponse errorResponse = new ErrorResponse(e);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().append(SecurityUtils.convertObjectToJson(errorResponse));
		}

	}

}