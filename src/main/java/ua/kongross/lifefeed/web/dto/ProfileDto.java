package ua.kongross.lifefeed.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kongross.lifefeed.database.entity.User;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String surname;
    private Collection<User> subscribers;
    private boolean subscribed;
}
