package travelling.api.app.model.request.blog;

import lombok.Data;

@Data
public class BlogSaveRequest {
    private String thumbnail;
    private String title;
    private String url;
    private String keyword;
    private String content;
    private String status;
    private String description;
}
