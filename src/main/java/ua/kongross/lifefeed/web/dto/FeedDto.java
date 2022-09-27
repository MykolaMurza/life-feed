package ua.kongross.lifefeed.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedDto {
    private List<FeedPost> posts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FeedPost {
        private Long id;
        private String text;
        private LocalDateTime createdAt;
        private String authorUsername;
        private Long authorId;
    }
}
