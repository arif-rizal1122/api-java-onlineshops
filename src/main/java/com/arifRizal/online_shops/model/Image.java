package com.arifRizal.online_shops.model;

import java.sql.Blob;

public class Image {
    private  Long id;
    private String fileName;
    private String fileType;
    private Blob image;
    private String downloadUrl;

    private Product product;

}