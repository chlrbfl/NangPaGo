package com.mars.NangPaGo.domain.recipe.controller;

import com.mars.NangPaGo.domain.recipe.entity.Recipe;
import com.mars.NangPaGo.domain.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recipe")
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // 레시피 목록 전체 조회

    // 레시피 상세 조회
    @GetMapping("/detail/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
       recipeService.toString();
    }



}
