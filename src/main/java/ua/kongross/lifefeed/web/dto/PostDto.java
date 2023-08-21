package ua.kongross.lifefeed.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String text;
    private LocalDateTime createdAt;
    private String authorUsername;
    private Long authorId;
    private boolean removable;
    private boolean votedByMe;
    private VoteType voteType;
    private int voteResult;
}
