package com.ashu.police;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken(httpServletRequest);
		try {
			System.out.println("Security filter started"+token+"--");
			if (token != null && jwtTokenProvider.validateToken(token)) {
				System.out.println("token valid");
				Authentication auth = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (CustomException ex) {
			// this is very important, since it guarantees the user is not authenticated at
			// all
			SecurityContextHolder.clearContext();
			httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
			return;
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}