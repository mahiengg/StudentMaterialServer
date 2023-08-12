package com.example.StudentMaterialServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserMaterialList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "material_name", nullable = false)
    private String materialName;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "register_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RegisterUser user;








    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
