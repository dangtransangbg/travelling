package travelling.api.app.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class BaseFilterRequest {
    private int pageIndex = 1;
    private int pageSize = 10;


    public PageRequest getPageRequest() {
        return PageRequest.of(this.pageIndex, this.pageSize);
    }
}
