package com.tonde.maisonchapback.models.workflows;


import com.tonde.maisonchapback.models.workflows.user.User;
import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID"))
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private User user;


}
