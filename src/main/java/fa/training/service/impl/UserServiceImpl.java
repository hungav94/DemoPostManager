package fa.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.model.User;
import fa.training.repository.UserRepository;
import fa.training.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void deleteUserById(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public void updateUserById(User user, String id) {
		userRepository.save(user);
	}

	@Override
	public void addUser(User user) {
		userRepository.insert(user);
	}

	public Iterable<User> save(List<User> users) {
		return userRepository.saveAll(users);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
