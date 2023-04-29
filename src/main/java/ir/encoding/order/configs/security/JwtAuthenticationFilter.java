package ir.encoding.order.configs.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import ir.encoding.order.view.dto.user.RoleDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String ERROR_MESSAGE = "{\"links\": [], \"content\": [\"%s\"]}";
	
	@Value("${jwt.signing.key}")
	private String signingKey;	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = request.getHeader("Authorization");
			SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(jwt)
					.getBody();
			
			String username = String.valueOf(claims.get("username"));
			List<LinkedHashMap<String, Object>> authorities = (List<LinkedHashMap<String, Object>>) claims.get("authorities");
			List<RoleDTO> roles = authorities.stream().map(hashMap -> {
				return RoleDTO.builder()
					.id(Long.parseLong(String.valueOf(hashMap.get("id"))))
					.name((String) hashMap.get("name"))
					.build();
			}).collect(Collectors.toList());
			var token = new UsernamePasswordAuthenticationToken(username, null, roles);
			SecurityContextHolder.getContext().setAuthentication(token);
			
			filterChain.doFilter(request, response);
			
		} catch(MalformedJwtException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().format(ERROR_MESSAGE, e.getMessage());
			response.flushBuffer();
		} catch(SignatureException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getWriter().format(ERROR_MESSAGE, e.getMessage());
			response.flushBuffer();						
		}
	}
		
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().startsWith("/tokens");
	}

	@Override
	protected boolean shouldNotFilterAsyncDispatch() {
		return super.shouldNotFilterAsyncDispatch();
	}

	@Override
	protected boolean shouldNotFilterErrorDispatch() {
		return super.shouldNotFilterErrorDispatch();
	}	
	
}
