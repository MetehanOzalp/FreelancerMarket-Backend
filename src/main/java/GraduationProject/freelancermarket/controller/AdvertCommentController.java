package GraduationProject.freelancermarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import GraduationProject.freelancermarket.model.dto.AdvertCommentAddDto;
import GraduationProject.freelancermarket.service.abstracts.AdvertCommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/advertComments/")
@RequiredArgsConstructor
public class AdvertCommentController {

	private final AdvertCommentService advertCommentService;

	@PostMapping("add")
	@PreAuthorize("hasRole('ROLE_EMPLOYER')" + "|| hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> add(@RequestBody AdvertCommentAddDto advertCommentAddDto) {
		var result = advertCommentService.add(advertCommentAddDto);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@RequestParam int advertCommentId) {
		var result = advertCommentService.delete(advertCommentId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getByAdvertId")
	public ResponseEntity<?> getByAdvertId(@RequestParam int advertId) {
		var result = advertCommentService.getByAdvertId(advertId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("getByFreelancerId")
	public ResponseEntity<?> getByFreelancerId(@RequestParam int freelancerId) {
		var result = advertCommentService.getByFreelancerId(freelancerId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

}
