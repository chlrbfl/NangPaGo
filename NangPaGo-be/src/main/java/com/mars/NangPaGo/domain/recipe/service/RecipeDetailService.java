package com.mars.NangPaGo.domain.recipe.service;

import com.mars.NangPaGo.domain.recipe.dto.RecipeResponseDto;
import com.mars.NangPaGo.domain.recipe.entity.Manual;
import com.mars.NangPaGo.domain.recipe.entity.ManualImage;
import com.mars.NangPaGo.domain.recipe.entity.Recipe;
import com.mars.NangPaGo.domain.recipe.repository.ManualImageRepository;
import com.mars.NangPaGo.domain.recipe.repository.ManualRepository;
import com.mars.NangPaGo.domain.recipe.repository.RecipeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeDetailService {

    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private final ManualRepository manualRepository;
    @Autowired
    private final ManualImageRepository manualImageRepository;

    public RecipeResponseDto getRecipeDetails(Long recipeId) {

        Recipe recipe = recipeRepository.findByRecipeId(recipeId)
            .orElseThrow(() -> new IllegalArgumentException("해당 레시피가 존재하지 않습니다."));

        List<Manual> manuals = manualRepository.findByRecipeIdOrderById(recipeId);
        List<ManualImage> manualImages = manualImageRepository.findByRecipeIdOrderById(recipeId);

        return RecipeResponseDto.builder()
            .id(recipe.getId())
            .name(recipe.getName())
            .ingredients(recipe.getIngredients())
            .cookingMethod(recipe.getCookingMethod())
            .category(recipe.getCategory())
            .hashTag(recipe.getHashTag())
            .mainImage(recipe.getMainImage())
            .stepImage(recipe.getStepImage())
            .manuals(manuals)
            .manualImages(manualImages)
            .createdAt(recipe.getCreatedAt())
            .updatedAt(recipe.getUpdatedAt())
            .build();
    }
}
