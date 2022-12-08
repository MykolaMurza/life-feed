package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.service.UserService;

@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public String getPosts(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        model.addAttribute("feed", postService.getPosts(user).getPosts());

        return "feed";
    }

    @PostMapping("/subscribe/{id}")
    public String subscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.subscribe(id, userDetails);

        return "redirect:/profile/" + id;
    }

    @PostMapping("/unsubscribe/{id}")
    public String unsubscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.unsubscribe(id, userDetails);

        return "redirect:/profile/" + id;
    }
}
