package switchtwentytwenty.project.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//Code adapted from https://www.javainuse.com/spring/boot-jwt and https://www.javainuse.com/spring/boot-jwt-mysql

@Entity
@Table(name = "user")
public class DAOUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;

    @Column
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public long getID() {
        return id;
    }
}
