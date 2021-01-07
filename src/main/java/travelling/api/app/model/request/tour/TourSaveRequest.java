package travelling.api.app.model.request.tour;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TourSaveRequest {
    private String name;
    private double price;
    private String title;
    private String content;
    private String keyword;
    private List<String> albums = new ArrayList<>();
    private String thumbnail;
    private String status;
    private int tourType;
    private long tourCategoryId;
    private String description;
    private String policy;
    private int regions;
    private String time;
    private String startPlace;
    private String destinationPlace;
    private String vehicle;
}
