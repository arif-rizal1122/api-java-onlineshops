package com.arifRizal.online_shops.controller;


import com.arifRizal.online_shops.dto.ImageDto;
import com.arifRizal.online_shops.exception.ResourceNotFoundException;
import com.arifRizal.online_shops.model.Image;
import com.arifRizal.online_shops.response.ApiResponse;
import com.arifRizal.online_shops.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files,@RequestParam Long productId) {
       try {

           List<ImageDto> imageDtos = imageService.saveImages(files, productId);
           return ResponseEntity.ok(new ApiResponse("Upload Success", imageDtos));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload Failed!", e.getMessage()));
       }
    };

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId){
        try {
            Image image = imageService.getImageById(imageId);
            ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file){
        try {
            Image image = imageService.getImageById(imageId);
            if (image != null){
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new ApiResponse("Updated Success!", null));
            }
        } catch (ResourceNotFoundException e) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Updated Failed!", null));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Updated Failed!", HttpStatus.INTERNAL_SERVER_ERROR));
    }



    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
        try {
            Image image = imageService.getImageById(imageId);
            if (image != null){
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ApiResponse("Delete Success!", null));
            }
        } catch (ResourceNotFoundException e) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Updated Failed!", null));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete Failed!", HttpStatus.INTERNAL_SERVER_ERROR));
    }







}
