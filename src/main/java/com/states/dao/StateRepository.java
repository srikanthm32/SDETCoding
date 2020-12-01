package com.states.dao;

import java.util.List;

import com.states.model.State;
import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<State, String> {

	List<State> findAllByNameContainingIgnoreCase(String countryName);

	List<State> findAllByCapitalIgnoreCase(String capital);

	List<State> findAllByCodeContainingIgnoreCase(String code);

}
