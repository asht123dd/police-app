package com.ashu.police;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final ModelMapper modelMapper;

	@PostMapping("/signin")
	public ResponseEntity<?> login(//
			@RequestParam String username, //
			@RequestParam String password) {
		try {
			return new ResponseEntity<String>(userService.signin(username, password), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/signup")
	public String signup(@RequestBody UserDataDTO user) {
//		if(user.getUsername()==)
		return userService.signup(modelMapper.map(user, AppUser.class));
	}

//	@DeleteMapping(value = "/{username}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public String delete(@PathVariable String username) {
//		userService.delete(username);
//		return username;
//	}

//	@GetMapping(value = "/{username}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//
//	public UserResponseDTO search( @PathVariable String username) {
//		return modelMapper.map(userService.search(username), UserResponseDTO.class);
//	}

//	@GetMapping(value = "/me")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//	@ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class, authorizations = {
//			@Authorization(value = "apiKey") })
//	@ApiResponses(value = { //
//			@ApiResponse(code = 400, message = "Something went wrong"), //
//			@ApiResponse(code = 403, message = "Access denied"), //
//			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
//	public UserResponseDTO whoami(HttpServletRequest req) {
//		return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
//	}

//	@GetMapping("/refresh")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//	public String refresh(HttpServletRequest req) {
//		return userService.refresh(req.getRemoteUser());
//	}
}
