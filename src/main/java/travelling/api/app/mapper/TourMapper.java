package travelling.api.app.mapper;


import lombok.Data;
import org.springframework.stereotype.Component;
import travelling.api.app.entity.Tour;
import travelling.api.app.entity.TourCategory;
import travelling.api.app.model.request.tour.TourSaveRequest;
import travelling.api.app.model.response.tour.TourDetailResponse;
import travelling.api.app.model.response.tour.TourResponse;
import travelling.api.app.repository.TourCategoryRepository;
import travelling.api.app.repository.TourRepository;
import travelling.api.app.service.TourCategoryService;
import travelling.api.app.util.BeanUtils;

import java.util.Optional;

import static travelling.api.app.util.ArrayUtils.convertToListFromString;
import static travelling.api.app.util.ArrayUtils.convertToString;


@Component
@Data
public class TourMapper implements Mapper {

    private final TourCategoryRepository tourRepository;

    public Tour convertToEntity(TourSaveRequest tourSaveRequest) {
        Tour tour = new Tour();
        BeanUtils.refine(tourSaveRequest, tour, BeanUtils::copyNonNull);
        tour.setIsHot(false);
        tour.setAlbums(convertToString(tourSaveRequest.getAlbums()));
        Optional<TourCategory> tourCategory = tourRepository.findById(tourSaveRequest.getTourCategoryId());
        tour.setTourCategory(tourCategory.get());

        return tour;
    }

    public TourResponse convertToResponse(Tour tour) {
        TourResponse tourResponse = new TourResponse();
        BeanUtils.refine(tour, tourResponse, BeanUtils::copyNonNull);

        return tourResponse;
    }

    public TourDetailResponse convertToDetailResponse(Tour tour) {
        TourDetailResponse tourDetailResponse = new TourDetailResponse();
        BeanUtils.refine(tour, tourDetailResponse, BeanUtils::copyNonNull);
//        tourDetailResponse.setAlbums(convertToListFromString(tour.getAlbums()));

        return tourDetailResponse;
    }


}
