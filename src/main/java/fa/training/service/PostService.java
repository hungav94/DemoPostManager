package fa.training.service;

import java.util.List;

import fa.training.model.Post;

public interface PostService {
	
	List<Post> getAllPosts();

	List<Post> findByUserId(Integer userId);
	
	Post getPostById(Integer id);
	
	void deletePostById(Integer id);
	
	void updatePostById(Post post, Integer id);
	
	void addPost(Post post);

}
