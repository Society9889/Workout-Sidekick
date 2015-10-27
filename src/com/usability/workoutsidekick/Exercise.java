package com.usability.workoutsidekick;

public class Exercise {

	private long id;
	
	private String name;
	private String mainMuscle;
	private String otherMuscles;
	private String description;
	private String equipment;
	private long favorite;
	private long userAdded;
	private String day;
	
	private boolean selected;
	
	public Exercise(){
		
	}
	
	public Exercise (String name, String mainMuscle, String otherMuscles, String description, String equipment, long favorite, long userAdded){
		this.name = name;
		this.mainMuscle = mainMuscle;
		this.otherMuscles = otherMuscles;
		this.description = description;
		this.equipment = equipment;
		this.favorite = favorite;
		this.userAdded = userAdded;
		selected = false;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainMuscle() {
		return mainMuscle;
	}

	public void setMainMuscle(String mainMuscle) {
		this.mainMuscle = mainMuscle;
	}

	public String getOtherMuscles() {
		return otherMuscles;
	}

	public void setOtherMuscles(String otherMuscles) {
		this.otherMuscles = otherMuscles;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getFavorite() {
		return favorite;
	}

	public void setFavorite(long favorite) {
		this.favorite = favorite;
	}
	
	public long getUserAdded() {
		return userAdded;
	}

	public void setUserAdded(long userAdded) {
		this.userAdded = userAdded;
	}

	public String toString(){
		return name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}
