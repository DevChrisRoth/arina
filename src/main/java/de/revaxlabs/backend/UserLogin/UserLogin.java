package de.revaxlabs.backend.UserLogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "user_login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin implements Serializable {
    @Id
    @GeneratedValue(generator="userLoginSeq")
    @SequenceGenerator(name="userLoginSeq",sequenceName="user_login_seq", allocationSize=1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, name = "passwordHash", columnDefinition = "passwordHash")
    private String password;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant created_at;

    @Column(nullable = true, updatable = true)
    @LastModifiedDate
    private Instant updated_at;

    @Column
    private Boolean confirmed;
}
