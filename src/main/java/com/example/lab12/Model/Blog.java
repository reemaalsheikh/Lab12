package com.example.lab12.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    //• id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    // title
    @NotEmpty(message = "title can not be null")
    // @Column(columnDefinition = "varchar(50) not null ")
    private String title;


    // body (Add All validation required).
    @NotEmpty(message = "body can not be null")
    // @Column(columnDefinition = "varchar(50) not null ")
    private String body;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;



}
