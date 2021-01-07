package travelling.api.app.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import travelling.api.app.entity.Tour;

public interface TourRepository extends BaseRepository<Tour, Long> {
    @Query("UPDATE Tour tour SET tour.isHot = :hot WHERE tour.id = :id")
    void updateTourHot(@Param("hot") boolean hot, @Param("id") long id);

    @Query("UPDATE Tour tour SET tour.status = :status WHERE tour.id = :id")
    void updateTourStatus(@Param("status") String status, @Param("id") long id);
}
