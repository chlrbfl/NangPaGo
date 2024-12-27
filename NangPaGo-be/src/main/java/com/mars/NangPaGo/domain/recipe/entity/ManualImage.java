package com.mars.NangPaGo.domain.recipe.entity;

import com.mars.NangPaGo.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "recipe_image")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ManualImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "id")
    private Recipe recipe;
}
