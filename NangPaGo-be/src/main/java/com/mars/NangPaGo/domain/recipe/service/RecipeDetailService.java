package com.mars.NangPaGo.domain.recipe.service;

import com.mars.NangPaGo.common.jpa.BaseEntity;
import com.mars.NangPaGo.domain.recipe.dto.RecipeResponseDto;
import com.mars.NangPaGo.domain.recipe.entity.Recipe;
import com.mars.NangPaGo.domain.recipe.repository.ManualImageRepository;
import com.mars.NangPaGo.domain.recipe.repository.ManualRepository;
import com.mars.NangPaGo.domain.recipe.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecipeDetailService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ManualRepository manualRepository;
    @Autowired
    private ManualImageRepository manualImageRepository;


    Recipe findByRecipeId(long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public RecipeDetailService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // 재료 정보 파싱 메소드
    public List<RecipeResponseDto> parseRecipeMaterials(String ingredients) {
        String[] splitData = ingredients.split("\\s", 2);
        if (splitData.length < 2) {
            throw new IllegalArgumentException("Invalid recipe materials");
        }

        String materials = splitData[1];
        String[] materialEntries = materials.split(",");
        List<RecipeResponseDto> materialList = new ArrayList<>();

        for (String materialEntry : materialEntries) {
            Pattern pattern = Pattern.compile("^(.+?)\\s(\\d+g)(\\s\\((.+)\\))?$");
            Matcher matcher = pattern.matcher(materialEntry);
            if (matcher.find()) {
                String materialName = matcher.group(1);
                String quantity = matcher.group(2);

                materialList.add(RecipeResponseDto.builder()
                        .material(materials)
                        .quantity(quantity)
                        .build());
            }
        }
        return materialList;
    }


}
