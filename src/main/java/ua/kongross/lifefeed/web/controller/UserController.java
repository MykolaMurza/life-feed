package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.kongross.lifefeed.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/block/{id}")
    public void subscribe(@PathVariable Long id, @RequestParam Boolean block,
                          @AuthenticationPrincipal UserDetails userDetails) {
        userService.block(id, block, userDetails);
    }

    @PostMapping("/subscribe/{id}")
    public void subscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.subscribe(id, userDetails);
    }

    @PostMapping("/unsubscribe/{id}")
    public void unsubscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.unsubscribe(id, userDetails);
    }
}
