package fa.training.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User findByUsername(String username);
	
	Optional<User> getByUsername(String username);
	
}
