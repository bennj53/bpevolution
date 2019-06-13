package com.whiterabbits.bpevolution.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Application implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String label;
    @Column
    private String code;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "applicationList")
    private Collection<BusinessProfile> businessProfile=new HashSet<>();

    public Application(String label, String code) {
        this.label = label;
        this.code = code;
    }

    //@OneToMany(mappedBy = "application")
    @OneToMany(targetEntity=AccessRight.class, mappedBy="application",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<AccessRight> AccessRightList=new HashSet<>();
}
