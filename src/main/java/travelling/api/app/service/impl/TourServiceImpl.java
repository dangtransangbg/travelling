package travelling.api.app.service.impl;


import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import travelling.api.app.common.constant.MessageConstant;
import travelling.api.app.common.role.RoleConstant;
import travelling.api.app.entity.Tour;
import travelling.api.app.entity.TourDetail;
import travelling.api.app.exception.AccessDeniedException;
import travelling.api.app.exception.ObjectNotFoundException;
import travelling.api.app.mapper.TourDetailMapper;
import travelling.api.app.mapper.TourMapper;
import travelling.api.app.model.request.tour.TourFilterRequest;
import travelling.api.app.model.request.tour.TourSaveRequest;
import travelling.api.app.model.request.tour.TourUpdateRequest;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.model.response.tour.TourDetailResponse;
import travelling.api.app.model.response.tour.TourResponse;
import travelling.api.app.repository.TourDetailRepository;
import travelling.api.app.repository.TourRepository;
import travelling.api.app.repository.specification.TourSpecification;
import travelling.api.app.service.TourService;
import travelling.api.app.service.UserService;
import travelling.api.security.jwt.model.JwtPayload;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TourServiceImpl implements TourService {

    private final TourMapper tourMapper;
    private final TourDetailMapper tourDetailMapper;
    private final UserService userService;
    private final TourRepository tourRepository;
    private final TourDetailRepository tourDetailRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TourSaveRequest tourSaveRequest) {
        Tour tour = tourMapper.convertToEntity(tourSaveRequest);

        tourRepository.save(tour);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTourToHot(TourUpdateRequest hotRequest) {
        tourRepository.updateTourHot(hotRequest.getHot(), hotRequest.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTourStatus(TourUpdateRequest tourUpdateRequest) {
        tourRepository.updateTourStatus(tourUpdateRequest.getStatus(), tourUpdateRequest.getId());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public TourDetailResponse getById(long id) {
        JwtPayload jwtPayload = userService.parseToken();

        Optional<Tour> tour = tourRepository.findById(id);

        if (!tour.isPresent()) {
            throw new ObjectNotFoundException(MessageConstant.TOUR_NOT_FOUND.value());
        }

        if (jwtPayload.getUserName().equals(tour.get().getCreatedBy())) {
            throw new AccessDeniedException(MessageConstant.ACCESS_DENIED.value());
        }

        TourDetailResponse tourDetailResponse = tourMapper.convertToDetailResponse(tour.get());

        return tourDetailResponse;
    }

    @Override
    public TourDetailResponse findTourById(Long id) {
        Optional<TourDetail> tour = tourDetailRepository.findById(id);
        TourDetailResponse tourDetailResponse = tourDetailMapper.convertToResponse(tour.get());
        return tourDetailResponse;
    }

    @Override
    public TourResponse findTourId(Long id) {
        Optional<Tour> tour = tourRepository.findById(id);
        TourResponse tourResponse = tourMapper.convertToResponse(tour.get());
        return tourResponse;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ListResponse<TourResponse> getAll(TourFilterRequest tourFilterRequest) {
        JwtPayload jwtPayload = userService.parseToken();
        if (jwtPayload.getRole().contains(RoleConstant.USER)) {
            tourFilterRequest.setCreatedBy(jwtPayload.getUserName());
        }
        Page<Tour> page = tourRepository.findAll(TourSpecification.tourFilter(tourFilterRequest), tourFilterRequest.getPageRequest());
        List<TourResponse> tourResponses = tourMapper.convertToList(tourMapper::convertToResponse, page.getContent());
        long totalItem = page.getTotalElements();

        return ListResponse.of(totalItem, tourResponses);
    }
}
