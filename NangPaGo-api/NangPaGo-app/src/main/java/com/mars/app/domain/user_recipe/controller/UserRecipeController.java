package com.mars.app.domain.user_recipe.controller;

import com.mars.app.aop.audit.AuditLog;
import com.mars.app.aop.auth.AuthenticatedUser;
import com.mars.app.aop.visit.VisitLog;
import com.mars.app.component.auth.AuthenticationHolder;
import com.mars.app.domain.user_recipe.dto.UserRecipeListResponseDto;
import com.mars.common.dto.ResponseDto;
import com.mars.app.domain.user_recipe.dto.UserRecipeRequestDto;
import com.mars.app.domain.user_recipe.dto.UserRecipeResponseDto;
import com.mars.app.domain.user_recipe.service.UserRecipeService;
import com.mars.common.dto.page.PageRequestVO;
import com.mars.common.dto.page.PageResponseDto;
import com.mars.common.enums.audit.AuditActionType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "유저 레시피 API", description = "유저 레시피 게시물 관련 API")
@RestController
@RequestMapping("/api/user-recipe")
public class UserRecipeController {

    private final UserRecipeService userRecipeService;

    @Operation(summary = "게시물 단일 조회")
    @GetMapping("/{id}")
    public ResponseDto<UserRecipeResponseDto> getUserRecipeById(@PathVariable Long id) {
        Long userId = AuthenticationHolder.getCurrentUserId();
        return ResponseDto.of(userRecipeService.getUserRecipeById(id, userId));
    }

    @VisitLog
    @Operation(summary = "게시물 목록 조회")
    @GetMapping("/list")
    public ResponseDto<PageResponseDto<UserRecipeListResponseDto>> list(PageRequestVO pageRequestVO) {
        Long userId = AuthenticationHolder.getCurrentUserId();
        return ResponseDto.of(userRecipeService.getPagedUserRecipes(userId, pageRequestVO));
    }

    @Operation(summary = "수정 페이지용 게시물 조회")
    @AuthenticatedUser
    @GetMapping("/edit/{id}")
    public ResponseDto<UserRecipeResponseDto> getPostForEdit(@PathVariable Long id) {
        Long userId = AuthenticationHolder.getCurrentUserId();
        return ResponseDto.of(userRecipeService.getPostForEdit(id, userId), "게시물을 성공적으로 가져왔습니다.");
    }

    @Operation(summary = "게시물 작성")
    @AuditLog(action = AuditActionType.USER_RECIPE_CREATE, dtoType = UserRecipeRequestDto.class)
    @AuthenticatedUser
    @PostMapping
    public ResponseDto<UserRecipeResponseDto> create(
        @ModelAttribute @Valid UserRecipeRequestDto requestDto,
        @RequestParam(value = "mainFile", required = false) MultipartFile mainFile,
        @RequestParam(value = "otherFiles", required = false) List<MultipartFile> otherFiles) {

        Long userId = AuthenticationHolder.getCurrentUserId();
        return ResponseDto.of(userRecipeService.createUserRecipe(requestDto, mainFile, otherFiles, userId),
            "게시물이 성공적으로 생성되었습니다.");
    }

    @Operation(summary = "게시물 수정")
    @AuditLog(action = AuditActionType.USER_RECIPE_UPDATE, dtoType = UserRecipeRequestDto.class)
    @AuthenticatedUser
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseDto<UserRecipeResponseDto> update(
        @PathVariable Long id,
        @RequestPart("requestDto") @Valid UserRecipeRequestDto requestDto,
        @RequestPart(value = "mainFile", required = false) MultipartFile mainFile,
        @RequestPart(value = "otherFiles", required = false) List<MultipartFile> otherFiles) {

        Long userId = AuthenticationHolder.getCurrentUserId();
        return ResponseDto.of(userRecipeService.updateUserRecipe(id, requestDto, mainFile, otherFiles, userId),
            "게시물이 성공적으로 수정되었습니다.");
    }

    @Operation(summary = "게시물 삭제")
    @AuditLog(action = AuditActionType.USER_RECIPE_DELETE)
    @AuthenticatedUser
    @DeleteMapping("/{id}")
    public ResponseDto<Void> delete(@PathVariable Long id) {
        Long userId = AuthenticationHolder.getCurrentUserId();
        userRecipeService.deleteUserRecipe(id, userId);
        return ResponseDto.of(null, "게시물이 성공적으로 삭제되었습니다.");
    }
}
