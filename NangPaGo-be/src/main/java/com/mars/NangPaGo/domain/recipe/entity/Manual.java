package com.mars.NangPaGo.domain.recipe.entity;

import com.mars.NangPaGo.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "recipe_manual")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manual extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manual;

    @ManyToOne
    @JoinColumn(name = "id")
    private Recipe recipe;
}
