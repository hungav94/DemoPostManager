package fa.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.model.Post;
import fa.training.repository.PostRepository;
import fa.training.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> findByUserId(Integer userId) {
		return postRepository.findByUserId(userId);
	}

	@Override
	public Post getPostById(Integer id) {
		Optional<Post> optional = postRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return new Post();
	}

	@Override
	public void deletePostById(Integer id) {
		postRepository.deleteById(id);
	}

	@Override
	public void updatePostById(Post post, Integer id) {
		postRepository.save(post);
	}

	@Override
	public void addPost(Post post) {
		postRepository.insert(post);
	}

}
