package pl.animeworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.animeworld.common.Message;
import pl.animeworld.model.Comment;
import pl.animeworld.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setUsername(username);
        comment.setContent(content);
        comment.setDateTime(localDateTime);
        commentRepository.save(comment);
        model.addAttribute("message", new Message("Sukces!", "Komentarz dodany"));
        return "message";
    }

    @RequestMapping("/article")
    public String allComments(Model model){
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "article";
    }


}
