package ua.kongross.lifefeed.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kongross.lifefeed.web.dto.VoteType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotePostRequest {
    private String id;
    private boolean vote; // true - vote, false - cancel vote
    private VoteType type;
}
