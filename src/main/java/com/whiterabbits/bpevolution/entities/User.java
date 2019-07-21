package com.whiterabbits.bpevolution.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    private String username;
    @Column
    private String last_name;
    @Column
    private String first_name;
    @Column
    private String password;
    @Column(name = "ENABLED")
    private boolean actived;
    @ManyToMany
    @JoinTable(name = "USERS_ROLE")
//            joinColumns = { @JoinColumn(name = "id_user") },
//            inverseJoinColumns = { @JoinColumn(name = "id_role") })
    private Collection<Role> roles = new HashSet<>();


    public User(String last_name, String first_name, String username, String password, boolean actived) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.username = username;
        this.password = password;
        this.actived = actived;
    }
}
