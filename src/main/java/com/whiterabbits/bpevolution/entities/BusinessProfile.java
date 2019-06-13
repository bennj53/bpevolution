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
//@ToString
@Entity
public class BusinessProfile implements Serializable {
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
            })
    @JoinTable(name = "Business_App_Links",
            joinColumns = { @JoinColumn(name = "id_Business_Profile") },
            inverseJoinColumns = { @JoinColumn(name = "id_Application") })
    private Collection<Application> applicationList=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "Business_AccessRight_Links",
            joinColumns = { @JoinColumn(name = "id_Business_Profile") },
            inverseJoinColumns = { @JoinColumn(name = "id_Access_Right") })
    private Collection<AccessRight> accessRightList=new HashSet<>();

    public BusinessProfile(String label, String code) {
        this.label = label;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ID : " + this.id + "\n"
                + "CODE : " + this.code + "\n"
                + "LABEL : " + this.label;
    }
}
