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

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String thumbnail;
    @Column
    private String title;
    @Column(unique = true)
    private String url;
    @Column
    private String keyword;
    @Column
    private String content;
    @Column
    private String status;
    @Column
    @CreatedBy
    private String createdBy;
    @Column
    private String description;
    @Column
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate createdDate;
    @Column
    @LastModifiedBy
    private String modifiedBy;
    @Column
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate modifiedDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.SELECT)
    private User user;
}
