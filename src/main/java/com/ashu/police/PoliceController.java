package com.ashu.police;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		complaint.setStatus("FILED");
		policeService.saveOrUpdate(complaint);
		return new ResponseEntity<Complaints>(complaint,HttpStatus.CREATED);
	}

	@GetMapping("/complaints/{complaintId}")
	public ResponseEntity<Complaints> getComplaintByComplaintId(@PathVariable long complaintId) {		
		return new ResponseEntity<Complaints>(policeService.getComplaintById(complaintId), HttpStatus.OK);
	}
}
