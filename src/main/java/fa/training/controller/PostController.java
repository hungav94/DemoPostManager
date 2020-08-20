package fa.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.model.Post;
import fa.training.service.PostService;

@CrossOrigin(value = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	PostService postService;

	@GetMapping("posts")
	public ResponseEntity<List<Post>> getAllPosts(@RequestParam(required = false) Integer userId) {
		System.out.println(userId);
		List<Post> posts = new ArrayList<>();
		if (userId == null) {
			posts = postService.getAllPosts();
		} else {
			posts = postService.findByUserId(userId);
		}
		return new ResponseEntity<>(posts, HttpStatus.OK);

	}

	@GetMapping("posts/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
		Post post = postService.getPostById(id);
		System.out.println(post);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@DeleteMapping("posts/{id}")
	public ResponseEntity<Void> deletePostById(@PathVariable Integer id) {
		postService.deletePostById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("posts/{id}")
	public ResponseEntity<Void> updatePostById(@RequestBody Post post, @PathVariable Integer id) {
		System.out.println(post);
		if (postService.getPostById(id) != null && post.getId().equals(id)) {
			postService.updatePostById(post, id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("posts")
	public ResponseEntity<Void> addPost(@RequestBody Post post) {
		postService.addPost(post);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
