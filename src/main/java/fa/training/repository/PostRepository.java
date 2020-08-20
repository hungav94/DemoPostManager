package fa.training.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fa.training.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, Integer>{
	
//	@Query(value = "{userId: '?0'}")
	List<Post> findByUserId(Integer userId);
	

}
