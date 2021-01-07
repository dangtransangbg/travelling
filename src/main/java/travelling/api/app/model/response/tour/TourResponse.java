package travelling.api.app.model.response.tour;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TourResponse {
    private Long id;
    private String name;
    private String title;
    private String keyword;
    private String thumbnail;
    private String createdBy;
    private String startPlace;
    private String destinationPlace;
    private String content;
    private LocalDate createdDate;
    private String vehicle;
}
