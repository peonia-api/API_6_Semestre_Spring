package fatec.sp.gov.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authorizations")
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "aut_id")
    private UUID id;

    @Column(name = "aut_name")
    private String name;

    @Column(name = "aut_permission_type")
    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

    public Authorization(String name, PermissionType permissionType) {
        this.name = name;
        this.permissionType = permissionType;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "authorizations")
    private Set<User> users = new HashSet<>();

    public Authorization() {}

    public Authorization(String name) {
        this.name = name;
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

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
