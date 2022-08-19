package lt.codeacademy.learn.library.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.learn.library.entities.Comment;

public interface CommentRepository extends JpaRepository <Comment, Integer>{

}
