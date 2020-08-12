package com.thirlilo.constants;

public enum KidFriendlyStatus {
	APPROVED("approved"), REJECTED("rejected"), UNKNOWN("unknown");

	private String name;

	private KidFriendlyStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
