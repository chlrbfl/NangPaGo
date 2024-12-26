package com.mars.NangPaGo.domain.recipe.repository;

import com.mars.NangPaGo.domain.recipe.entity.ManualImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualImageRepository extends JpaRepository<ManualImage, Long> {
    List<ManualImage> findByRecipeIdOrderById(Long recipeId);
}
