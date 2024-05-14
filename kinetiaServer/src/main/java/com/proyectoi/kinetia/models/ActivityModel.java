package com.proyectoi.kinetia.models;

import com.proyectoi.kinetia.domain.Activity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "activities")
public class ActivityModel {

    public enum Category {
        AIRE_LIBRE,
        ARTE,
        AVENTURA,
        BARES,
        CURSOS_Y_TALLERES,
        DEPORTE,
        EXPERIENCIAS,
        GASTRONOMIA,
        MUSICA,
        OCIO,
        OFERTAS,
        SALUD_Y_BIENESTAR
    }

    public ActivityModel() {

    }

    public ActivityModel(Activity activity) {
        this.title = activity.getTitle();
        this.description = activity.getDescription();
        this.picture = activity.getPicture();
        this.date = activity.getDate();
        this.createdAt = activity.getCreatedAt();
        this.price = activity.getPrice();
        this.location = activity.getLocation();
        this.category = activity.getCategory();
        this.featured = activity.getFeatured() != null ? activity.getFeatured() : false;
        this.vacancies = activity.getVacancies();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;

    @Column(nullable = false)
    private LocalDateTime date;

    @CreatedDate
    private Instant createdAt = Instant.now();

    @Column
    private BigDecimal price;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private Boolean featured;

    @Column(nullable = false)
    private int vacancies;

    @ManyToMany(mappedBy = "activitiesFav", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserModel> usersWhoFav = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservations",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserModel> reservations = new HashSet<>();


    // GETTER AND SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public Set<UserModel> getUsersWhoFav() {
        return usersWhoFav;
    }

    public void setUsersWhoFav(Set<UserModel> usersWhoFav) {
        this.usersWhoFav = usersWhoFav;
    }

    public Set<UserModel> getReservations() {
        return reservations;
    }

    public void setReservations(Set<UserModel> reservations) {
        this.reservations = reservations;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "ActivityModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", date=" + date +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", category=" + category +
                ", featured=" + featured +
                ", vacancies=" + vacancies +
                ", usersWhoFav=" + usersWhoFav.size() +
                ", reservations=" + reservations.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityModel that = (ActivityModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Boolean addReservation(UserModel user) {
        return reservations.add(user);
    }

    public void cancelReservation(UserModel user) {
        reservations.remove(user);
    }
}
