package com.bakcell.nomre.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Javidan Alizada
 */
public interface FileUploadService {

    void upload(MultipartFile file);
}
