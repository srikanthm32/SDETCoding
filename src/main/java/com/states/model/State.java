package com.states.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class State implements Serializable {

	private static final long serialVersionUID = 1043853426968534459L;

	@Id
	@Column(name = "name", unique = true)
	private String name;
	@Column(name = "code", unique = true)
	private String code;
	private String capital;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public State() {
	}

	public State(String name, String code, String captial) {
		this.name = name;
		this.capital = captial;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "State [name=" + name + ", code=" + code + ", capital=" + capital+ "]";
	}

}
