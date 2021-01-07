package travelling.api.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String policy;
    @Column
    private String keyword;
    @Column(columnDefinition = "TEXT")
    private String albums;
    @Column
    private String thumbnail;
    @Column
    private String status;
    @Column
    private Boolean isHot;
    @Column
    private int tourType;
    @Column
    private int regions;
    @Column
    private String time;
    @Column
    private String startPlace;
    @Column
    private String destinationPlace;
    @Column
    private String vehicle;

    @Column
    @CreatedBy
    private String createdBy;
    @Column
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate createdDate;
    @Column
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate modifiedDate;
    @Column
    @LastModifiedBy
    private String modifiedBy;

    @OneToMany(mappedBy = "tour")
    @Fetch(FetchMode.SELECT)
    private List<TourDetail> tourDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tour_category_id")
    private TourCategory tourCategory;
}
