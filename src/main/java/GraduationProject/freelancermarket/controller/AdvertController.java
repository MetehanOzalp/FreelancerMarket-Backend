package GraduationProject.freelancermarket.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import GraduationProject.freelancermarket.model.dto.AdvertAddDto;
import GraduationProject.freelancermarket.model.dto.AdvertSearchFilter;
import GraduationProject.freelancermarket.model.dto.AdvertFilter;
import GraduationProject.freelancermarket.model.dto.AdvertUpdateDto;
import GraduationProject.freelancermarket.service.abstracts.AdvertService;
import GraduationProject.freelancermarket.utils.ErrorDataResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/adverts/")
@RequiredArgsConstructor
public class AdvertController {

	private final AdvertService advertService;

	@PostMapping("add")
	@PreAuthorize("hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> add(@Valid @ModelAttribute AdvertAddDto advertAddDto) {
		var result = advertService.add(advertAddDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("delete")
	@PreAuthorize("hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> delete(@RequestParam int advertId) {
		var result = advertService.delete(advertId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("update")
	@PreAuthorize("hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> update(@ModelAttribute AdvertUpdateDto advertUpdateDto) {
		var result = advertService.update(advertUpdateDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		var result = advertService.getAll();
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getByUserName")
	public ResponseEntity<?> getByUserName(@RequestParam String userName) {
		var result = advertService.getByUserName(userName);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getByFreelancerId")
	public ResponseEntity<?> getByFreelancerId(@RequestParam int freelancerId) {
		var result = advertService.getByFreelancerId(freelancerId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getBySubCategoryId")
	public ResponseEntity<?> getBySubCategoryId(@RequestParam int subCategoryId) {
		var result = advertService.getBySubCategoryId(subCategoryId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getMostPopularJobAdverts")
	public ResponseEntity<?> getMostPopularJobAdverts() {
		var result = advertService.getMostPopularJobAdverts();
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("getByFilter")
	public ResponseEntity<?> getBySubCategoryNameFilter(@RequestParam String subCategoryName,
			@RequestBody AdvertFilter advertFilter) {
		var result = advertService.getByPageNumberAndFilter(advertFilter.getPage(), subCategoryName, advertFilter);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("getBySearchFilter")
	public ResponseEntity<?> getBySearchFilter(@RequestBody AdvertSearchFilter advertSearchFilter) {
		var result = advertService.getByPageNumberAndSearchFilter(advertSearchFilter.getPage(), advertSearchFilter);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getById")
	public ResponseEntity<?> getById(@RequestParam int id) {
		var result = advertService.getById(id);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
		System.out.println("Geldi");
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
	}

}
