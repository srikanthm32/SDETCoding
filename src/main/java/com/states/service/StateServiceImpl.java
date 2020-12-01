package com.states.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.states.dao.StateRepository;
import com.states.model.State;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	StateRepository stateRepo;

	@Override
	public State addState(State state) {
		System.out.println("Before Add " + state);
		return stateRepo.save(state);
	}

	@Override
	public List<State> getAllStates() {
		return StreamSupport.stream(stateRepo.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public List<State> getAllStatesByName(String stateName) {
		return stateRepo.findAllByNameContainingIgnoreCase(stateName);
	}

	@Override
	public List<State> getStatesByCode(String code) {
		return stateRepo.findAllByCodeContainingIgnoreCase(code);
	}


}
