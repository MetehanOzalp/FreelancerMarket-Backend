package GraduationProject.freelancermarket.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import GraduationProject.freelancermarket.model.dto.FavoriteAddDto;
import GraduationProject.freelancermarket.service.abstracts.AdvertService;
import GraduationProject.freelancermarket.service.abstracts.FavoriteService;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/favorities/")
@RequiredArgsConstructor
public class FavoriteController {

	private final FavoriteService favoriteService;
	private final AdvertService advertService;

	@PostMapping("add")
	@PreAuthorize("hasRole('ROLE_EMPLOYER')" + "|| hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> add(@Valid @RequestBody FavoriteAddDto favoriteAddDto) {
		var result = favoriteService.add(favoriteAddDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("delete")
	@PreAuthorize("hasRole('ROLE_EMPLOYER')" + "|| hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> delete(@RequestParam int userId, @RequestParam int advertId) {
		var result = favoriteService.delete(userId, advertId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getByUserId")
	@PreAuthorize("hasRole('ROLE_EMPLOYER')" + "|| hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> getByUserId(@RequestParam int userId) {
		var favorities = favoriteService.getByUserId(userId);
		if (!favorities.isSuccess()) {
			return new ResponseEntity<Object>(favorities, HttpStatus.BAD_REQUEST);
		}
		List<Integer> ids = new ArrayList<Integer>();
		for (var favorite : favorities.getData()) {
			ids.add(favorite.getAdvertId());
		}
		var result = advertService.getByIdIn(ids);
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
