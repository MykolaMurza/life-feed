package ua.kongross.lifefeed.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainPostRequest {
    private String id;
    private String complain;
}
