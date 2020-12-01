package com.states.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.states.model.State;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.states.dao.StateRepository;

@Component
public class DataService {

	@Autowired
	StateRepository stateRepo;

	@EventListener(ApplicationReadyEvent.class)
	private void readJSON() {
		JSONParser jparser = new JSONParser();
		try {
			File datafile = new ClassPathResource("data/states.json").getFile();
			Reader rd = new FileReader(datafile);
			JSONArray jsonArray = (JSONArray) jparser.parse(rd);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String name = (String) jsonObject.get("name");
				State state = buildState(jsonObject);
				stateRepo.save(state);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private State buildState(JSONObject stateFromJson) {
		State state = new State();
		state.setName((String) stateFromJson.get("name"));
		state.setCapital((String) stateFromJson.get("capital"));
		state.setCode((String) stateFromJson.get("code"));
		return state;
	}
}
