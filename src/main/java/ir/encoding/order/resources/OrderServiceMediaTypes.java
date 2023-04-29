package ir.encoding.order.resources;

import java.nio.charset.StandardCharsets;

import org.springframework.http.MediaType;

public class OrderServiceMediaTypes {

	public static final String V1 = "application/vnd.order-service.api.v1+json;charset=UTF-8";
	public static final MediaType APPLICATION_RULE_MANAGER_V1 = new MediaType("application", "vnd.order-service.api.v1+json", StandardCharsets.UTF_8);
	
}
