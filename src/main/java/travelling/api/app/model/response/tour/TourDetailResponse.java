package travelling.api.app.model.response.tour;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TourDetailResponse {
    private Long id;
    private double price;
    private double priceBaby;
    private double priceChildren;
    private double startTime;
    private long seats;
    private long duration;
    private String discount;
}

