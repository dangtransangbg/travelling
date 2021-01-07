package travelling.api.app.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import travelling.api.app.entity.Tour;
import travelling.api.app.entity.TourDetail;
import travelling.api.app.model.response.tour.TourDetailResponse;
import travelling.api.app.model.response.tour.TourResponse;
import travelling.api.app.util.BeanUtils;

@Component
@Data
public class TourDetailMapper implements Mapper{

    public TourDetailResponse convertToResponse(TourDetail tour) {
        TourDetailResponse tourResponse = new TourDetailResponse();
        BeanUtils.refine(tour, tourResponse, BeanUtils::copyNonNull);

        return tourResponse;
    }
}
