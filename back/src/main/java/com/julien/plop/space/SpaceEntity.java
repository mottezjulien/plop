package com.julien.plop.space;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST1_SPACE")
public class SpaceEntity {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String label;

    @ManyToMany
    @JoinTable(name = "test1_space_point_rel")
    private Set<SpacePointEntity> points = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

   public Set<SpacePointEntity> getPoints() {
        return points;
    }

    public void setPoints(Set<SpacePointEntity> points) {
        this.points = points;
    }
}
