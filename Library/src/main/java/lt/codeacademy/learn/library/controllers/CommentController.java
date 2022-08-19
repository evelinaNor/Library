package lt.codeacademy.learn.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.codeacademy.learn.library.entities.Comment;
import lt.codeacademy.learn.library.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/post") //returns comment that is created
	public ResponseEntity<Comment> createComment (@RequestBody Comment comment){
		Comment comm = commentService.save(comment);
		return ResponseEntity.ok(comm);
	}
	
	
}
