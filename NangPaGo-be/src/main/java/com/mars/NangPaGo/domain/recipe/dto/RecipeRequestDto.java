package com.mars.NangPaGo.domain.recipe.dto;

import com.mars.NangPaGo.domain.recipe.entity.Manual;
import com.mars.NangPaGo.domain.recipe.entity.ManualImage;
import com.mars.NangPaGo.domain.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RecipeRequestDto {

    private Recipe recipe;
    private Manual manual;
    private ManualImage manualImage;




}
