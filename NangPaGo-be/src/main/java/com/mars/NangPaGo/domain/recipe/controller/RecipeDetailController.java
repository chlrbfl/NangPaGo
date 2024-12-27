package com.mars.NangPaGo.domain.recipe.controller;

import com.mars.NangPaGo.domain.recipe.dto.RecipeResponseDto;
import com.mars.NangPaGo.domain.recipe.service.RecipeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recipe")
@RestController
public class RecipeDetailController {

    @Autowired
    private RecipeDetailService recipeDetailService;

    // 레시피 상세정보 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeDetail(@PathVariable Long id) {
        RecipeResponseDto responseDto = recipeDetailService.getRecipeDetails(id);
        return ResponseEntity.ok(responseDto);
    }
}
