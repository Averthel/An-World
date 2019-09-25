package pl.animeworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.animeworld.common.Message;
import pl.animeworld.model.Comment;
import pl.animeworld.repository.CommentRepository;

@Controller
public class CommentController {


    private CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam String content, @RequestParam String username, Model model ){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUsername(username);
        commentRepository.save(comment);
        model.addAttribute("message", new Message("Sukces!", "Komentarz dodany"));
        return "message";
    }


}
