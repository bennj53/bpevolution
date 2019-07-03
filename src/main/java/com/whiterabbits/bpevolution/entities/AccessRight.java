package com.whiterabbits.bpevolution.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class AccessRight {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String label;

    @Column
    private String type;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "accessRightList")
    private Collection<BusinessProfile> businessProfile=new HashSet<>();

    //@OneToOne
    //@JoinColumn(name="id_Application")
    @ManyToOne()
    @JoinColumn(name="id_Application", referencedColumnName = "id", insertable = false, updatable = true)
    private Application application;

    public AccessRight(String label, String code){
        this.label = label;
        this.code = code;
    }

}
