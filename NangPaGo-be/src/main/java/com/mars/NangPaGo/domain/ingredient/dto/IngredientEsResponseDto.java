package com.mars.NangPaGo.domain.ingredient.dto;

import com.mars.NangPaGo.domain.ingredient.entity.IngredientEs;
import lombok.Builder;

@Builder
public record IngredientEsResponseDto(
    Long ingredientId,
    String highlightedName,
    Double matchScore
) {

    public static IngredientEsResponseDto of(IngredientEs ingredientEs, String highlightedName) {
        return IngredientEsResponseDto.builder()
            .ingredientId(ingredientEs.getIngredientId())
            .highlightedName(highlightedName)
            .build();
    }

    public static IngredientEsResponseDto of(IngredientEs ingredientEs, String highlightedName, double matchScore) {
        return IngredientEsResponseDto.builder()
            .ingredientId(ingredientEs.getIngredientId())
            .highlightedName(highlightedName)
            .matchScore(matchScore)
            .build();
    }
}

