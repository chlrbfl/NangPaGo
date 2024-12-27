package com.mars.NangPaGo.domain.recipe.repository;

import com.mars.NangPaGo.domain.recipe.entity.Manual;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualRepository extends JpaRepository<Manual, Long> {
    List<Manual> findByRecipeIdOrderById(Long id);
}
