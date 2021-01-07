package travelling.api.app.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import travelling.api.app.entity.Blog;

public interface BlogRepository extends BaseRepository<Blog, Long> {
    @Query("UPDATE Blog bl SET  bl.status = :status WHERE bl.id = :id")
    void update(@Param("id") long id, @Param("status") String status);
}
