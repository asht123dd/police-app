package com.ashu.police;

import java.net.URI;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PoliceController {

	@Autowired
	PoliceService policeService;

	@GetMapping("/strings")
	public ResponseEntity<List<String>> getStrings() {
		List<String> strings = Arrays.asList("string1", "string2", "string3");
		return ResponseEntity.ok(strings);
	}

	@GetMapping("/complaints")
	@PreAuthorize("hasRole('OFFICER')")
	public ResponseEntity<List<Complaints>> getComplaints() {
		return ResponseEntity.ok(policeService.getAllComplaints());
	}

	@PostMapping("/complaints")
	public ResponseEntity<Complaints> makeComplaint(@RequestBody Complaints complaint) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		complaint.setStatus("FILED");
		complaint.setUsername(authentication.getName());
		policeService.saveOrUpdate(complaint);
		return new ResponseEntity<Complaints>(complaint, HttpStatus.CREATED);
	}

	@GetMapping("/complaints/{complaintId}")
	public ResponseEntity<?> getComplaintByComplaintId(@PathVariable long complaintId, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasUserRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_OFFICER"));
		System.out.println(authentication.getName());
		String user = authentication.getName();
		if (hasUserRole) {
			return new ResponseEntity<Complaints>(policeService.getComplaintById(complaintId), HttpStatus.OK);
		} else {
			Complaints complaint = policeService.getComplaintById(complaintId);
			if (user.equals(complaint.getUsername())) {
				return new ResponseEntity<Complaints>(complaint, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
		}
	}
}
