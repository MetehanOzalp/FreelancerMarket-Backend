package GraduationProject.freelancermarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import GraduationProject.freelancermarket.service.abstracts.WalletTransactionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/walletTransactions/")
@RequiredArgsConstructor
public class WalletTransactionController {

	private final WalletTransactionService walletTransactionService;

	@GetMapping("getByUserId")
	@PreAuthorize("hasRole('ROLE_EMPLOYER')" + "|| hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> getByUserId(@RequestParam int userId) {
		var result = walletTransactionService.getByUserId(userId);
		if (!result.isSuccess()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(result);
	}

}
