package fatec.sp.gov.login.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "are_id")
    private UUID id;

    @Column(name = "are_name")
    private String name;

    @Column(name = "are_description")
    private String description;

    public Area(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}