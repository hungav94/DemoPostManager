package fa.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.User;
import fa.training.service.UserService;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("users/{username")
	public ResponseEntity<User> findByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("users/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
		userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<Void> updateUserById(@RequestBody User user, @PathVariable String id) {
		if (userService.getUserById(id) != null && user.getId().equals(id)) {
			userService.updateUserById(user, id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("users")
	public ResponseEntity<Void> addUser(@RequestBody User user) {
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
