package iuh.fit.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String name, String email, String password, LocalDate date, Gender gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
        this.gender = gender;
    }
}
