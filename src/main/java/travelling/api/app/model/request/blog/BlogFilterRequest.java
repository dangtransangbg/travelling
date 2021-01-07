package travelling.api.app.model.request.blog;

import lombok.Data;
import travelling.api.app.model.request.BaseFilterRequest;

@Data
public class BlogFilterRequest extends BaseFilterRequest {
    private String userName;
    private String url;
    private String status;
}
