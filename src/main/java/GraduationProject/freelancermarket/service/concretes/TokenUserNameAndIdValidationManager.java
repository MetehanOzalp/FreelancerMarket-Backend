package GraduationProject.freelancermarket.service.concretes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import GraduationProject.freelancermarket.core.jwt.JwtUtil;
import GraduationProject.freelancermarket.service.abstracts.TokenUserNameAndIdValidationService;
import GraduationProject.freelancermarket.service.abstracts.UserService;
import GraduationProject.freelancermarket.utils.DataResult;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.ErrorResult;
import GraduationProject.freelancermarket.utils.Result;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import GraduationProject.freelancermarket.utils.SuccessResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenUserNameAndIdValidationManager implements TokenUserNameAndIdValidationService {

	private final HttpServletRequest request;
	private final JwtUtil jwtUtil;
	private final UserService userService;

	@Override
	public Result userIdAndTokenUserNameVerification(int userId) {
		var userName = getUserNameFromToken();
		if (!userName.isSuccess()) {
			return new ErrorResult(userName.getMessage());
		}
		var user = userService.getById(userId);
		if (!user.isSuccess()) {
			return new ErrorResult(user.getMessage());
		}
		if (!userName.getData().equals(user.getData().getUserName())) {
			return new ErrorResult("User Id ile kullanıcı adı farklı");
		}
		return new SuccessResult();
	}

	public DataResult<String> getUserNameFromToken() {
		final String requestTokenHeader = request.getHeader("Authorization");
		if (requestTokenHeader != null) {
			String jwtToken = requestTokenHeader.substring(7);
			String userName = jwtUtil.getUsernameFromToken(jwtToken);
			return new SuccessDataResult<String>(userName, null);
		}
		return new ErrorDataResult<String>(null, "Token bulunamadı");
	}

}
