package adminstuff.CSC340Project;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    private Long id;
    private String username;
    private String password;

    @ElementCollection
    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String admin) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String adminpassword) {
        this.password = password;
    }

}