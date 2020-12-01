package com.states.controller;

import java.net.URI;
import java.util.List;

import com.states.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.states.exception.StateNotFoundException;
import com.states.service.StateService;

@RestController
@RequestMapping(path = "/rest/")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping("/allStates")
	public List<State> getAllStates() {
		return stateService.getAllStates();
	}

	@PostMapping("/state")
	public ResponseEntity<Object> createCountry(@RequestBody State country) {
		State createdState = stateService.addState(country);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdState.getName()).toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping("/name/{state}")
	public ResponseEntity<Object> getState(@RequestParam(defaultValue = "false") String fullText,
										   @PathVariable String state) {
		List<State> existingCountries = stateService.getAllStatesByName( state);
		if (existingCountries.size() > 0) {
			return ResponseEntity.ok(existingCountries);
		} else {
			throw new StateNotFoundException("State not found with Name " + state);
		}
	}

	@RequestMapping("/code/{code}")
	public ResponseEntity<Object> getCountryByCode(@PathVariable String code) {
		List<State> existingCountries = stateService.getStatesByCode(code);
		if (existingCountries.size() > 0) {
			return ResponseEntity.ok(existingCountries);
		} else {
			throw new StateNotFoundException("State not found with code " + code);
		}

	}



}
