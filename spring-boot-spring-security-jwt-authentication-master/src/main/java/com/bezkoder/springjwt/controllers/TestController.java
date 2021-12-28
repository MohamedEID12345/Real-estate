package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.RealEstate;
import com.bezkoder.springjwt.models.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {


	@GetMapping("/all")
	public String allAccess() {
		return "";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<RealEstate> userAccess() {
		RealEstate r = new RealEstate("hello","gettt",1000,"#");
		RealEstate r1 = new RealEstate("hello","gettt",1000,"#");

		return new ResponseEntity<RealEstate>(r, HttpStatus.OK);
		//return new ResponseEntity<RealEstate>(new RealEstate("mohamed","ffff",1000.0,"#"),HttpStatus.OK);

	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
