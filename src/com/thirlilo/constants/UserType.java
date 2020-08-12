package com.thirlilo.constants;

public enum UserType {

	USER("user"), EDITOR("editor"), CHIEF_EDITOR("chiefeditor");

	private String name;

	private UserType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
