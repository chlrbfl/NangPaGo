package com.mars.app.domain.firebase.controller;

import com.mars.app.domain.firebase.service.ImageOptimizationService;
import com.mars.common.dto.ResponseDto;
import com.mars.app.domain.firebase.service.FirebaseStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "이미지 업로드 API", description = "Firebase Storage에 이미지 업로드 API")
@RequestMapping("/api/image")
@RestController
public class FirebaseStorageController {

    private final FirebaseStorageService firebaseStorageService;
    private final ImageOptimizationService imageOptimizationService;

    @PostMapping("/upload")
    @Operation(summary = "이미지 업로드", description = "Firebase Storage에 이미지를 업로드합니다.")
    public ResponseDto<String> uploadFile(@RequestPart("file") MultipartFile file) {
        String fileUrl = firebaseStorageService.uploadFile(file);
        return ResponseDto.of(fileUrl, "이미지 업로드 성공");
    }

    @PostMapping("/optimize")
    public ResponseDto<String> optimizeImages() {
        return ResponseDto.of(imageOptimizationService.optimizeAndUpdateImages());
    }
}
