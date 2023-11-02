package ua.kongross.lifefeed.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @ToString.Exclude
    private transient List<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_sub",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sub_id", referencedColumnName = "id")}
    )
    private Collection<User> subscribers;

    @ManyToMany(mappedBy = "subscribers", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
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
