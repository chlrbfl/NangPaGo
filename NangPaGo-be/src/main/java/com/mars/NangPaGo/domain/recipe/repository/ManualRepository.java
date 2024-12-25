package com.mars.NangPaGo.domain.recipe.repository;

import com.mars.NangPaGo.domain.recipe.entity.Manual;
import com.mars.NangPaGo.domain.recipe.entity.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualRepository extends JpaRepository<Manual, Long> {

    List<Manual> findByRecipe(Long id);
}
