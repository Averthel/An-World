package pl.animeworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.animeworld.model.Comment;
import pl.animeworld.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class CommentController {


    private CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam String content, @RequestParam String username, Model model, RedirectAttributes redirectAttr ){
        Comment comment = new Comment();
        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setUsername(username);
        comment.setContent(content);
        comment.setDateTime(localDateTime);
        try {
            commentRepository.save(comment);
            redirectAttr.addFlashAttribute("message", "Komentarz dodany");
        }catch (Exception e){
            redirectAttr.addFlashAttribute("message", "Ups, nie udało się dodać komentarza");
        }
        return "redirect:article";
    }

    @RequestMapping("/article")
    public String allComments(Model model){
        List<Comment> comments = commentRepository.findAll();
        model.addAttribute("comments", comments);
        return "article";
    }


}
