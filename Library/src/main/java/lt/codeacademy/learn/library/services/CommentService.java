package lt.codeacademy.learn.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.learn.library.entities.Comment;
import lt.codeacademy.learn.library.interfaces.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	public Comment save(Comment comment) {		
		return commentRepository.save(comment);
	}

	
}
