package Username.example.Username.Controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String name;
	@Column
	private String password;
	
	

	 private int failedLoginAttempts = 0;

	    @Column(name = "account_locked")  // Explicitly map this field to a single column name
	    private boolean accountLocked = false;

	    // Getter and Setter for failedLoginAttempts
	    public int getFailedLoginAttempts() {
	        return failedLoginAttempts;
	    }

	    public void setFailedLoginAttempts(int failedLoginAttempts) {
	        this.failedLoginAttempts = failedLoginAttempts;
	    }

	    // Getter and Setter for accountLocked
	    public boolean isAccountLocked() {
	        return accountLocked;
	    }

	    public void setAccountLocked(boolean accountLocked) {
	        this.accountLocked = accountLocked;
	    }
	

}
