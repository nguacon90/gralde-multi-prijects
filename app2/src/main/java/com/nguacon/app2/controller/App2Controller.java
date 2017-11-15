package com.nguacon.app2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by minh on 14/11/2017.
 */
@RestController
public class App2Controller
{

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public ResponseEntity<?> sayHello() {
		return new ResponseEntity<Object>("HelloWorld", HttpStatus.OK);
	}

}
