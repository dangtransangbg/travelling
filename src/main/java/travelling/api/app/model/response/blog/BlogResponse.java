package travelling.api.app.model.response.blog;

import lombok.Data;

@Data
public class BlogResponse {
    private long id;
    private String url;
    private String thumbnail;
    private String title;
    private String description;
}
