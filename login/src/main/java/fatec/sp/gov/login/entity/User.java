package fatec.sp.gov.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private UUID id;

    @Column(name = "usr_name")
    private String name;

    @Column(name = "usr_surname")
    private String surname;

    @Column(name = "usr_password")
    private String password;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_function")
    private String function;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<RedZones> redZones = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Area> areas = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "uau_user_authorization",
            joinColumns = { @JoinColumn(name = "usr_id")},
            inverseJoinColumns = { @JoinColumn(name = "aut_id") }
    )
    private Set<Authorization> authorizations = new HashSet<Authorization>();


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

    public Set<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(Set<Authorization> authorizations) {
        this.authorizations = authorizations;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password=" + password +
                ", email=" + email +
                ", function=" + function +
                '}';
    }
}
