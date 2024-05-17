package fatec.sp.gov.login.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "redZones")
public class RedZones {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "red_id")
    private UUID id;

    @Column(name = "red_name")
    private String name;

    @Column(name = "red_description")
    private String description;

    @Column(name = "red_cameraSpot")
    private String cameraSpot;

    @Column(name = "red_personLimit")
    private int personLimit;

    @Column(name = "red_responsibleGuard")
    private String responsibleGuard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public RedZones(UUID id, String name, String description, String cameraSpot, int personLimit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cameraSpot = cameraSpot;
        this.personLimit = personLimit;
    }

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
}
