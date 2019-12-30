package sec.project.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Account extends AbstractPersistable<Long> {


    @Column(unique = true)
    private String username;
    private String password;

    @OneToOne
    private Signup signup;

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

    public Signup getSignup() {
        return signup;
    }

    public void setSignup(Signup signup) {
        this.signup = signup;
    }


}
