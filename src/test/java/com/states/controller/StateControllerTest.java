package com.states.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.states.model.State;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.states.service.StateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath*:spring-test.xml" })
public class StateControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Mock
	private StateService stateService;

	private MockMvc mockMvc;

	private State state1;
	private State state2;
	private State state3;

	private List<State> states;

	@InjectMocks
	private StateController stateController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(stateController).build();
		state1 = new State();
		state1.setName("North carolina");
		state2 = new State();
		state2.setName("California");
		state3 = new State();
		state3.setName("Alabama");
		states = new ArrayList<State>();
		states.add(state1);
		states.add(state2);
		states.add(state3);
	}

	@Test
	public void getAllStates() throws Exception {
		when(stateService.getAllStates()).thenReturn(states);
		mockMvc.perform(get("/rest/allStates")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());
		verify(stateService, times(1)).getAllStates();
		verifyNoMoreInteractions(stateService);
	}

	@Test
	public void getAllCountriesByName() throws Exception {
		when(stateService.getAllStatesByName( "Alabama")).thenReturn(states);
		mockMvc.perform(get("/rest/name/{state}", "Alabama")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getAllCountriesByCode() throws Exception {
		when(stateService.getStatesByCode("AL")).thenReturn(states);
		mockMvc.perform(get("/rest/code/{codes}", "AL")).andExpect(status().isOk()).andDo(print());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
