package com.proyectoi.kinetia.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "activities")
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String title;

    @Column (nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;

    @Column (nullable = false)
    private LocalDateTime date;

    @CreatedDate
    private Instant createdAt;

    @Column
    private Float price;

    @Column (nullable = false)
    private String location;

    @Column (nullable = false)
    private Category category;

    @Column (nullable = false)
    private Boolean featured;

    @Column (nullable = false)
    private int vacancies;

    @ManyToMany(mappedBy = "activitiesFav", fetch = FetchType.LAZY)
    private List<UserModel> usersWhoFav = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "reservations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<UserModel> reservations = new ArrayList<>();


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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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

    public List<UserModel> getUsersWhoFav() {
        return usersWhoFav;
    }

    public void setUsersWhoFav(List<UserModel> usersWhoFav) {
        this.usersWhoFav = usersWhoFav;
    }

    public List<UserModel> getReservations() {
        return reservations;
    }

    public void setReservations(List<UserModel> reservations) {
        this.reservations = reservations;
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
}
