package travelling.api.app.model.request.tour;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourUpdateRequest {
    private long id;
    private Boolean hot;
    private String status;
}
