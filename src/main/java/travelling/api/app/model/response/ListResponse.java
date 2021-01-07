package travelling.api.app.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ListResponse<T> extends GeneralResponse implements Serializable {
    private long totalItem;
    private List<T> data;
}
