package Utility.Models.Ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestModel {
	private String userEmail;
	private String userPassword;
}
