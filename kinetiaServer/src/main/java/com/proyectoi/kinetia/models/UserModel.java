package com.proyectoi.kinetia.models;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "roleType", nullable = false)
    private RoleModel rol;

    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String secondSurname;
    @Column
    private LocalDate birthDate;
    @Column
    private String profilePicture;
    @Column
    private String company;
    @Column
    private String cif;
    @Column
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ActivityModel> activitiesOffered = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "favorite_user_activities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<ActivityModel> activitiesFav = new HashSet<>();

    @ManyToMany(mappedBy = "reservations", fetch = FetchType.LAZY)
    private Set<ActivityModel> activitiesReserved = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AdvertisementModel> advertisements = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<MessageModel> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<MessageModel> receivedMessages = new ArrayList<>();

    public UserModel() {}

    public UserModel(String email, RoleModel rol, String password, String name, String surname,
                     String secondSurname, LocalDate birthDate, String profilePicture, String company,
                     String cif, String address) {
        this.email = email;
        this.rol = rol;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.birthDate = birthDate;
        this.profilePicture = profilePicture;
        this.company = company;
        this.cif = cif;
        this.address = address;
    }

    public Boolean addFav(ActivityModel activity) {
        return activitiesFav.add(activity);
    }

    public void deleteFav(ActivityModel activity) {
        activitiesFav.remove(activity);
    }


    // Setter y Getter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleModel getRole() {
        return rol;
    }

    public void setRole(RoleModel rol) {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public List<ActivityModel> getActivitiesOffered() {
        return activitiesOffered;
    }

    public void setActivitiesOffered(List<ActivityModel> activitiesOffered) {
        this.activitiesOffered = activitiesOffered;
    }

    public Set<ActivityModel> getActivitiesFav() {
        return activitiesFav;
    }

    public void setActivitiesFav(Set<ActivityModel> activitiesFav) {
        this.activitiesFav = activitiesFav;
    }

    public Set<ActivityModel> getActivitiesReserved() {
        return activitiesReserved;
    }

    public void setActivitiesReserved(Set<ActivityModel> activitiesReserved) {
        this.activitiesReserved = activitiesReserved;
    }

    public List<AdvertisementModel> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<AdvertisementModel> advertisements) {
        this.advertisements = advertisements;
    }

    public List<MessageModel> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<MessageModel> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<MessageModel> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<MessageModel> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public String fullName() {
        if (this.company == null || this.company.isEmpty())
            return this.name + " " + this.surname + " " + this.secondSurname;
        else
            return this.company;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", rol=" + rol +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", birthDate=" + birthDate +
                ", profilePicture='" + profilePicture + '\'' +
                ", company='" + company + '\'' +
                ", cif='" + cif + '\'' +
                ", adress='" + address + '\'' +
                ", activitiesOffered=" + activitiesOffered.size() +
                ", activitiesFav=" + activitiesFav.size() +
                ", activitiesReserved=" + activitiesReserved.size() +
                ", advertisements=" + advertisements.size() +
                ", sentMessages=" + sentMessages.size() +
                ", receivedMessages=" + receivedMessages.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }






}
