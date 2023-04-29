package ir.encoding.order.view.dto.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ir.encoding.order.view.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
*
* @author S M Hossein Jamali
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
public class PersonDTO extends DTO {

    private Long id;
    
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String nationalCode;
	private String cardNumber;	
	private Long userId;
}
