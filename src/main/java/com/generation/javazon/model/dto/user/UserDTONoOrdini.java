package com.generation.javazon.model.dto.user;

import com.generation.javazon.auth.model.UserInDb;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTONoOrdini {

	protected String username, password;

	public UserDTONoOrdini(UserInDb u) {
		this.username= u.getUsername();
		this.password= u.getPassword();
	}

	public UserInDb convertToUser() {
		UserInDb user= new UserInDb();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

}