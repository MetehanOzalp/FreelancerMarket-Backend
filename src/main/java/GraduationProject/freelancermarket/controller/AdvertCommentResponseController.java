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

import GraduationProject.freelancermarket.model.dto.AdvertCommentResponseAddDto;
import GraduationProject.freelancermarket.service.abstracts.AdvertCommentResponseService;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/advertCommentResponses/")
@RequiredArgsConstructor
public class AdvertCommentResponseController {

	private final AdvertCommentResponseService advertCommentResponseService;

	@PostMapping("add")
	@PreAuthorize("hasRole('ROLE_EMPLOYER')" + "|| hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> add(@Valid @RequestBody AdvertCommentResponseAddDto advertCommentResponseAddDto) {
		var result = advertCommentResponseService.add(advertCommentResponseAddDto);
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
