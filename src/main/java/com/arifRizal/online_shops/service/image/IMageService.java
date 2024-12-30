package com.arifRizal.online_shops.service.image;

import com.arifRizal.online_shops.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IMageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    Image saveImage(MultipartFile file, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
