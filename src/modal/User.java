package modal;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User implements Serializable {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name" )
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public User() {
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(int userId, String userName, String password, String email) {
         
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

   
   
}
