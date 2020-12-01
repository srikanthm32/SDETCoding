package com.states.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.states.model.State;
import com.states.service.StateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.states.dao.StateRepository;

public class StateServiceTest {

	@InjectMocks
    StateServiceImpl StateService;

	@Mock
	private StateRepository stateRepository;
	private State state1;
	private State state2;
	private State state3;

	private List<State> states = new ArrayList<>();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
        state1 = new State();
        state1.setName("North Carolina");
        state1.setCode("NC");
        state1.setCapital("Raleigh");
        state2 = new State();
        state2.setName("South Carolina");
        state2.setCode("SC");
        state2.setCapital("Columbia");
        state3 = new State();
        state3.setName("South Dakota");
        state3.setCode("SD");
        state3.setCapital("Pierre");
		states.add(state1);
		states.add(state2);
		states.add(state3);
	}

	@Test
	public void getAllStatesTest() throws ParseException {
		when(stateRepository.findAll()).thenReturn(states);
		List<State> states = StateService.getAllStates();
		assertEquals(3, states.size());
		verify(stateRepository, times(1)).findAll();
	}

	@Test
	public void creatStatesTest() throws ParseException {

		when(stateRepository.save(state1)).thenReturn(state1);
		StateService.addState(state1);
		verify(stateRepository, times(1)).save(state1);
	}

	@Test
	public void getAllStatesByNameTest() {
		when(stateRepository.findAllByNameContainingIgnoreCase(state1.getName())).thenReturn(states);
		List<State> states = StateService.getAllStatesByName(state1.getName());
		assertEquals(3, states.size());
		verify(stateRepository, times(1)).findAllByNameContainingIgnoreCase(state1.getName());
	}

	@Test
	public void getStatesByCodeTest() {
		when(stateRepository.findAllByCodeContainingIgnoreCase("AL")).thenReturn(states);
		List<State> states = StateService.getStatesByCode("AL");
		assertEquals(3, states.size());
		verify(stateRepository, times(1)).findAllByCodeContainingIgnoreCase("AL");
	}

	@Test
	public void getStatesByInvalidCodeTest() {
		when(stateRepository.findAllByCodeContainingIgnoreCase("ALLLL")).thenReturn(states);
		List<State> states = StateService.getStatesByCode("ALLLLL");
		assertEquals(0, states.size());
	}

	@Test
	public void getStatesByInvalidNameTest() {
		when(stateRepository.findAllByNameContainingIgnoreCase("ALLLL")).thenReturn(states);
		List<State> states = StateService.getStatesByCode("ALLLLL");
		assertEquals(0, states.size());
	}



}
