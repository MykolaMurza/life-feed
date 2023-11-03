package ua.kongross.lifefeed.service;

public interface AuthService {
    void signUp(String username, String password, String email);

    String login(String username, String password);
}
