package travelling.api.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TourDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String identity;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate startTime;
    @Column
    private long price;
    @Column
    private long priceChildren;
    @Column
    private long priceBaby;
    @Column
    private long seats;
    @Column
    private long duration;
    @Column
    private String discount;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
}
