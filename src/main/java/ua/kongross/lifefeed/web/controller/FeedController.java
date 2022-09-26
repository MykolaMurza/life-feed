package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kongross.lifefeed.service.PostService;

@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final PostService postService;

    @GetMapping
    public String getPosts(Model model) {
        model.addAttribute("feed", postService.getPosts().getPosts());

        return "feed";
    }
}
