package travelling.api.app.model.request.tour;

import lombok.Data;
import travelling.api.app.model.request.BaseFilterRequest;

@Data
public class TourFilterRequest extends BaseFilterRequest {
    private String status;
    private Boolean isHot;
    private String tourType;
    private double fromPrice;
    private double toPrice;
    private double fromDate;
    private double toDate;
    private String createdBy;
}
