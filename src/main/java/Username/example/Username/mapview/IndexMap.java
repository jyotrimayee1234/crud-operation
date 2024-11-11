package Username.example.Username.mapview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Username.example.Username.Controller.UserApplication;
import Username.example.Username.Reporsitory.Reporsitory;


@Controller
public class IndexMap {

    @Autowired
    private Reporsitory repository;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String userRegistration(@ModelAttribute UserApplication userApplication, Model model) {
        System.out.print(userApplication.toString());
        String name1=userApplication.getName();
        System.out.print(userApplication.getPassword());
        
        if (name1 == null) {
            // If the user does not exist
            model.addAttribute("error", "User does not exist.");
            return "login";
        }
        if (userApplication.isAccountLocked()) {
            model.addAttribute("error", "Account is locked due to multiple failed login attempts.");
            return "login";
        }
        if (userApplication.getPassword().equals(userApplication.getPassword())) {
            // Reset failed attempts on successful login
        	userApplication.setFailedLoginAttempts(0);
            repository.save(userApplication);
            model.addAttribute("message", "Login successful.");
            return "submit"; // Redirect to the success page
        } else {
            // Increment the failed login attempts
            int attempts = userApplication.getFailedLoginAttempts() + 1;
            userApplication.setFailedLoginAttempts(attempts);
            int MAX_FAILED_ATTEMPTS=3;
			if (attempts >= MAX_FAILED_ATTEMPTS) {
				userApplication.setAccountLocked(true);
                model.addAttribute("error", "Account locked due to too many failed attempts.");
            } else {
                // Show remaining attempts if account is not yet locked
                model.addAttribute("error", "Incorrect password. Attempts remaining: " + (MAX_FAILED_ATTEMPTS - attempts));
            }

        repository.save(userApplication);
        model.addAttribute("message", "User registration successful.");
        
        return "submit";
    }
    }
}
