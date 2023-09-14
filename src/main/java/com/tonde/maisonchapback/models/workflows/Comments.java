package com.tonde.maisonchapback.models.workflows;


import com.tonde.maisonchapback.models.workflows.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "_comments")
@Schema(
        name = "Comments",
        description = "Comments model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = Comments.class,
        example = """
                {
                  "id": "integer",
                  "house": "House",
                  "user": "User",
                  "comment": "string",
                  "dateCreation": "LocalDateTime",
                  "dateModification": "LocalDateTime"
                }""",
        requiredProperties = {"id", "house", "user", "comment", "dateCreation"}

)
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_HOUSE_ID_Comment"))
    private House house;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Comment"))
    private User user;


    @Column(nullable = false)
    private String comment;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created_at")
    private LocalDateTime dateCreation;

    @Column(columnDefinition = "TIMESTAMP", name = "date_modification")
    private LocalDateTime dateModification;


}
