package ir.encoding.order.configs.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.security.Keys;
import ir.encoding.order.view.dto.user.RoleDTO;

@Component
public class JwtUtils {

	private static final long EXPIRATION_DURATION = 60 * 1000; // One minute
	
	@Value("${jwt.signing.key}")
	private String signingKey;

	private final JacksonSerializer jacksonSerializer;
		
	public JwtUtils(ObjectMapper objectMapper) {
		jacksonSerializer = new JacksonSerializer(objectMapper);
	}
	
	public String getToken(String username, List<RoleDTO> roles) {
		SecretKey secretKey = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
		String jwt = Jwts.builder()
				.serializeToJsonWith(jacksonSerializer)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
				.setClaims(Map.of("username", username, "authorities", roles))				
				.signWith(secretKey)
				.compact();
		return jwt;
	}
	
}
