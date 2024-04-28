package com.proyectoi.kinetia.models;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column	
	private String email;
	@Column
	private String rol;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String surmane;
	@Column
	private String secondSurmane;
	@Column
	private String birthDate;
	@Column
	private String profilePicture;
	@Column
	private String company;
	@Column
	private String cif;
	@Column
	private String adress;

//	private ArrayList<Activity> activitiesReserved;
//	private ArrayList<Activity> activitiesFav;
//	private ArrayList<Activity> activitiesOffered;
//	private ArrayList<Advertisement> advertisements;
//	private ArrayList<Chat> chats;
//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurmane() {
		return surmane;
	}

	public void setSurmane(String surmane) {
		this.surmane = surmane;
	}

	public String getSecondSurmane() {
		return secondSurmane;
	}

	public void setSecondSurmane(String secondSurmane) {
		this.secondSurmane = secondSurmane;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

//	public ArrayList<Activity> getActivitiesReserved() {
//		return activitiesReserved;
//	}
//
//	public void setActivitiesReserved(ArrayList<Activity> activitiesReserved) {
//		this.activitiesReserved = activitiesReserved;
//	}
//
//	public ArrayList<Activity> getActivitiesFav() {
//		return activitiesFav;
//	}
//
//	public void setActivitiesFav(ArrayList<Activity> activitiesFav) {
//		this.activitiesFav = activitiesFav;
//	}
//
//	public ArrayList<Activity> getActivitiesOffered() {
//		return activitiesOffered;
//	}
//
//	public void setActivitiesOffered(ArrayList<Activity> activitiesOffered) {
//		this.activitiesOffered = activitiesOffered;
//	}
//
//	public ArrayList<Advertisement> getAdvertisements() {
//		return advertisements;
//	}
//
//	public void setAdvertisements(ArrayList<Advertisement> advertisements) {
//		this.advertisements = advertisements;
//	}
//	
//	public ArrayList<Chat> getChats() {
//		return chats;
//	}
//
//	public void setChats(ArrayList<Chat> chats) {
//		this.chats = chats;
//	}

}
