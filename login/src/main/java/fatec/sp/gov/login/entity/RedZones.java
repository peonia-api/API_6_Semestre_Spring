package fatec.sp.gov.login.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "redZones")
public class RedZones {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "red_id")
    @JsonView(Views.Public.class)
    private UUID id;

    @Column(name = "red_name")
    @JsonView(Views.Public.class)
    private String name;

    @Column(name = "red_description")
    @JsonView(Views.Public.class)
    private String description;

    @Column(name = "red_cameraSpot")
    @JsonView(Views.Public.class)
    private String cameraSpot;

    @Column(name = "red_personLimit")
    @JsonView(Views.Public.class)
    private int personLimit;

    @Column(name = "red_responsibleGuard")
    @JsonView(Views.Public.class)
    private String responsibleGuard;

    @ManyToOne
    @JoinColumn(name = "area_id")
    @JsonView(Views.Internal.class)
    private Area area;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.Internal.class)
    private User user;

    public RedZones(UUID id, String name, String description, String cameraSpot, int personLimit, Area area, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cameraSpot = cameraSpot;
        this.personLimit = personLimit;
        this.area = area;
        this.user = user;
    }

    public RedZones() {}

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

    public String getCameraSpot() {
        return cameraSpot;
    }

    public void setCameraSpot(String cameraSpot) {
        this.cameraSpot = cameraSpot;
    }

    public int getPersonLimit() {
        return personLimit;
    }

    public void setPersonLimit(int personLimit) {
        this.personLimit = personLimit;
    }

    public String getResponsibleGuard() {
        return responsibleGuard;
    }

    public void setResponsibleGuard(String responsibleGuard) {
        this.responsibleGuard = responsibleGuard;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
