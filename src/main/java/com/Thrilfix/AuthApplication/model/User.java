package com.Thrilfix.AuthApplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_mst_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "user_email",unique = true,length = 300)
    private String email;
    @Column(name = "user_name",length = 500)
    private String name;
    private String password;
    private String image;
    private boolean enable;
    private Instant createdAt =Instant.now();
    private Instant updatedAt = Instant.now();

    @Enumerated(EnumType.STRING)
    private Provider provider=Provider.LOCAL;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    protected void onCreate(){
        Instant now=Instant.now();
        if(createdAt==null){
            updatedAt=now;
        }
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }

}
