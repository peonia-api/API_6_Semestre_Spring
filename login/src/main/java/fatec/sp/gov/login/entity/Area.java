package fatec.sp.gov.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "are_id")
    @JsonView(Views.Public.class)
    private UUID id;

    @Column(name = "are_name")
    @JsonView(Views.Public.class)
    private String name;

    @Column(name = "are_description")
    @JsonView(Views.Public.class)
    private String description;

    @Column(name = "are_responsibleManager")
    @JsonView(Views.Public.class)
    private String responsibleManager;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.Public.class)
    private User user;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonView(Views.Public.class)
    private Set<RedZones> redZones = new HashSet<>();

    public Area(UUID id, String name, String description, String responsibleManager, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.responsibleManager = responsibleManager;
        this.user = user;
    }

    public Area() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibleManager() {
        return responsibleManager;
    }

    public void setResponsibleManager(String responsibleManager) {
        this.responsibleManager = responsibleManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<RedZones> getRedZones() {
        return redZones;
    }

    public void setRedZones(Set<RedZones> redZones) {
        this.redZones = redZones;
    }
}