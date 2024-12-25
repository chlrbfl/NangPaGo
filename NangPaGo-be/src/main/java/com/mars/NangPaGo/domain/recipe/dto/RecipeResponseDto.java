package com.mars.NangPaGo.domain.recipe.dto;

import com.mars.NangPaGo.common.jpa.BaseEntity;
import com.mars.NangPaGo.domain.recipe.entity.Manual;
import com.mars.NangPaGo.domain.recipe.entity.ManualImage;
import com.mars.NangPaGo.domain.recipe.entity.Recipe;
import com.mars.NangPaGo.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecipeResponseDto extends BaseEntity {

    private Long id;
    private String name;
    private String ingredients;
    private String cookingMethod;
    private String category;
    private String hashTag;
    private String mainImage;
    private String stepImage;
    private List<Manual> manuals;
    private List<ManualImage> manualImages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // private String material; // 재료 이름
    // private String quantity; // 수량
}
