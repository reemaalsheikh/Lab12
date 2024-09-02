package com.example.lab12.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

   //id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // username
    @NotEmpty(message = "username can not be null")
    // @Column(columnDefinition = "varchar(20) not null unique")
    private String username;


    // password (Add All validation required).
    @NotEmpty(message = "password can not be null")
    //  @Column(columnDefinition = "varchar(50) not null")
    private String password;

    @NotEmpty(message = "role can not be null")
    //   @Column(columnDefinition = "varchar(20) not null ")
    private String role;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    private Set<Blog> blogs;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
