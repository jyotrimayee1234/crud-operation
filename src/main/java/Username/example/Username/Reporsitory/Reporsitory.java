package Username.example.Username.Reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import Username.example.Username.Controller.UserApplication;

public interface Reporsitory extends JpaRepository<UserApplication, Integer> {
	UserApplication findByname(String name);

}
