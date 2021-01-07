package travelling.api.app.model.response.blog;

import lombok.Data;

@Data
public class BlogDetailResponse {
    private long id;
    private String thumbnail;
    private String title;
    private String url;
    private String keyword;
    private String content;
    private String status;
    private String description;
}
