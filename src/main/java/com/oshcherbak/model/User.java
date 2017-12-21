package com.oshcherbak.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor()
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "ID_SEQ")
    @SequenceGenerator(sequenceName = "USERS_SEQUENCE", allocationSize = 1, name = "ID_SEQ")
    private Long id;

    @Column(name = "firstName")
    @NonNull
    private String firstName;
    @NonNull
    @Column(name = "lastName")
    private String lastName;
}
