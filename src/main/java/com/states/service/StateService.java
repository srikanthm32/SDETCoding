package com.states.service;

import java.util.List;

import com.states.model.State;

public interface StateService {

	State addState(State country);

	List<State> getAllStates();

	List<State> getAllStatesByName(String stateName);
	
	List<State> getStatesByCode(String code);

}
