package fa.training.service;

import java.util.List;

import fa.training.model.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUserById(String id);
	
	void deleteUserById(String id);
	
	void updateUserById(User user, String id);
	
	void addUser(User user);
	
	public Iterable<User> save(List<User> users);
	
	User findByUsername(String username);
}
