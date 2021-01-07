package travelling.api.app.model.request.blog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogUpdateStatus {
    private long id;
    private String status;
}
