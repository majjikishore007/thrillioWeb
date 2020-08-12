package com.thirlo.entites;

import com.thirlilo.constants.KidFriendlyStatus;

public abstract class BookMark {
	private long id;
	private String title;
	private String profileUrl;
	private KidFriendlyStatus kidfriendlyStatus = KidFriendlyStatus.UNKNOWN;
	private  User KidFriendlyMarkedBy;
	private  User sharedBy;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public abstract boolean isKidFriendlyEligible();

	public KidFriendlyStatus getKidfriendlyStatus() {
		return kidfriendlyStatus;
	}

	public void setKidfriendlyStatus(KidFriendlyStatus kidfriendlyStatus) {
		this.kidfriendlyStatus = kidfriendlyStatus;
	}

	public User getKidFriendlyMarkedBy() {
		return KidFriendlyMarkedBy;
	}

	public void setKidFriendlyMarkedBy(User kidFriendlyMarkedBy) {
		KidFriendlyMarkedBy = kidFriendlyMarkedBy;
	}

	public User getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(User sharedBy) {
		this.sharedBy = sharedBy;
	}

}
