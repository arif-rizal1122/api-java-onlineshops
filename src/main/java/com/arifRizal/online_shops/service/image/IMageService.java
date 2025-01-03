package com.arifRizal.online_shops.service.image;

import com.arifRizal.online_shops.dto.ImageDto;
import com.arifRizal.online_shops.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
