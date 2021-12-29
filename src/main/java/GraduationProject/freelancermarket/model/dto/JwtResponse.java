package GraduationProject.freelancermarket.model.dto;

import GraduationProject.freelancermarket.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private User user;
	private String jwtToken;

}
