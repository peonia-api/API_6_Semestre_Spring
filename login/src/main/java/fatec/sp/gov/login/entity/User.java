package fatec.sp.gov.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usr_id")
    @JsonView(Views.Public.class)
    private UUID id;

    @Column(name = "usr_name")
    @JsonView(Views.Public.class)
    private String name;

    @Column(name = "usr_surname")
    @JsonView(Views.Public.class)
    private String surname;

    @Column(name = "usr_password")
    @JsonView(Views.Internal.class)
    private String password;

    @Column(name = "usr_email")
    @JsonView(Views.Public.class)
    private String email;

    @Column(name = "usr_function")
    @JsonView(Views.Public.class)
    private String function;

    @Column(name = "usr_permission_type")
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Public.class)
    private PermissionType permissionType;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<RedZones> redZones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Area> areas = new HashSet<>();

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public Set<RedZones> getRedZones() {
        return redZones;
    }

    public void setRedZones(Set<RedZones> redZones) {
        this.redZones = redZones;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
}

