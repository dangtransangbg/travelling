package travelling.api.app.model.request.user;

import lombok.Data;

@Data
public class UserRequest {
    private String sort;
    private Integer page;
    private Integer size;

    public UserRequest() {
    }

    public UserRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
