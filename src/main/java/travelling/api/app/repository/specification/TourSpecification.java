package travelling.api.app.repository.specification;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import travelling.api.app.entity.Tour;
import travelling.api.app.model.request.tour.TourFilterRequest;

public class TourSpecification {

    public static Specification<Tour> tourFilter(TourFilterRequest tourFilterRequest) {
        return Specification.where(withFromPrice(tourFilterRequest.getFromPrice()))
                .and(withToPrice(tourFilterRequest.getToPrice()))
                .and(withTourType(tourFilterRequest.getTourType()))
                .and(withStatus(tourFilterRequest.getStatus()))
                .and(withCreatedBy(tourFilterRequest.getCreatedBy()));
    }

    private static Specification<Tour> withFromPrice(double fromPrice) {
        if (fromPrice == 0) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), fromPrice);
    }

    private static Specification<Tour> withToPrice(double toPrice) {
        if (toPrice == 0) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), toPrice);
    }

    private static Specification<Tour> withTourType(String tourType) {
        if (StringUtils.isBlank(tourType)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("tourType"), tourType);
    }

    private static Specification<Tour> withStatus(String status) {
        if (StringUtils.isBlank(status)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    private static Specification<Tour> withCreatedBy(String createdBy) {
        if (StringUtils.isBlank(createdBy)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdBy"), createdBy);
    }
}
