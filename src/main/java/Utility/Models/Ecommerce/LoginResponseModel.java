package Utility.Models.Ecommerce;

import lombok.Data;

@Data
public class LoginResponseModel {
	private String token;
	private String userId;
	private String message;
}
