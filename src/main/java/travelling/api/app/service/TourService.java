package travelling.api.app.service;


import travelling.api.app.model.request.tour.TourFilterRequest;
import travelling.api.app.model.request.tour.TourSaveRequest;
import travelling.api.app.model.request.tour.TourUpdateRequest;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.model.response.tour.TourDetailResponse;
import travelling.api.app.model.response.tour.TourResponse;

public interface TourService {
    void save(TourSaveRequest tourSaveRequest);

    void updateTourToHot(TourUpdateRequest tourUpdateRequest);

    void updateTourStatus(TourUpdateRequest tourUpdateRequest);

    TourDetailResponse getById(long id);

    TourDetailResponse findTourById(Long id);

    TourResponse findTourId(Long id);

    ListResponse<TourResponse> getAll(TourFilterRequest tourFilterRequest);
}
