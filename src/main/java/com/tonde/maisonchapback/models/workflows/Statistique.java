package com.tonde.maisonchapback.models.workflows;

import com.tonde.maisonchapback.models.workflows.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_statistique")
public class Statistique{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "nbr_favoris", columnDefinition = "int default 0")
    private String nbrvisite;

    @Column(nullable = false, name = "nbr_like", columnDefinition = "int default 0")
    private String nbrLike;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Statistique"))
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Statistique"))
    private User user;

}
