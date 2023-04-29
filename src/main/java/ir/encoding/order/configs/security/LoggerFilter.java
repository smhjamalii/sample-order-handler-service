package ir.encoding.order.configs.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component @Slf4j
public class LoggerFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = request.getHeader("username") != null ? request.getHeader("username") :
			request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : 
			SecurityContextHolder.getContext().getAuthentication() != null ? SecurityContextHolder.getContext().getAuthentication().getName() :	null;
				
		filterChain.doFilter(request, response);
		log.info("[{}|{}][{}]: {}", request.getRemoteAddr(), username, request.getRequestURI(), response.getStatus());		
	}

}
