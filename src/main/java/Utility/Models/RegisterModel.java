package Utility.Models;

import lombok.*;

@Getter
@Setter
public class RegisterModel {
	private String email;
	private String password;
	private String error;
	private String token;
	private int id;
	private RegisterModel(){}

	public  static RegisterModel init() {
		return new RegisterModel();
	}

	public RegisterModel SetEmail(String email){
		 this.email = email;
		 return this;
	}

	public RegisterModel SetPassword(String password){
		 this.password = password;
		 return this;
	}
}
