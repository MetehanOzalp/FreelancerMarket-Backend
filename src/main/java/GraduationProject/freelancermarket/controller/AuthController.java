package GraduationProject.freelancermarket.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import GraduationProject.freelancermarket.core.jwt.JwtService;
import GraduationProject.freelancermarket.model.dto.ChangePasswordDto;
import GraduationProject.freelancermarket.model.dto.EmployerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.FreelancerForRegisterDto;
import GraduationProject.freelancermarket.model.dto.UserForLoginDto;
import GraduationProject.freelancermarket.service.abstracts.AuthService;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import GraduationProject.freelancermarket.utils.SuccessDataResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final JwtService jwtService;

	@PostMapping("registerForEmployer")
	public ResponseEntity<?> registerForEmployer(@Valid @RequestBody EmployerForRegisterDto employerForRegisterDto) {
		var result = authService.registerForEmployer(employerForRegisterDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("registerForFreelancer")
	public ResponseEntity<?> registerForFreelancer(
			@Valid @RequestBody FreelancerForRegisterDto freelancerForRegisterDto) {
		var result = authService.registerForFreelancer(freelancerForRegisterDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("login")
	public ResponseEntity<?> createJwtToken(@Valid @RequestBody UserForLoginDto userForLoginDto) throws Exception {
		var result = authService.login(userForLoginDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(new SuccessDataResult<Object>(jwtService.createJwtToken(userForLoginDto)));
	}

	@PostMapping("changePassword")
	@PreAuthorize("hasRole('ROLE_EMPLOYER')" + "|| hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
		var result = authService.changePassword(changePasswordDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
	}

}
