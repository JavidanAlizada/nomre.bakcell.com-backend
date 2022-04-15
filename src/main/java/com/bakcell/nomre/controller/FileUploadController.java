package com.bakcell.nomre.controller;

import com.bakcell.nomre.model.response.ReservationResponse;
import com.bakcell.nomre.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author Javidan Alizada
 */
@Api(tags = "file-upload")
@PropertySource("classpath:messages.properties")
@RequestMapping("/api/v1/upload")
@Slf4j
@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final FileUploadService service;

    @PostMapping
    @ApiOperation(value = "${api.file-upload.controller.upload}")
    public ResponseEntity<ReservationResponse> reserve(@ApiParam("MultipartFile") @Valid @RequestBody MultipartFile file) {
        log.info("file upload accepts multiPart file request");
        this.service.upload(file);
        return ResponseEntity.ok().build();
    }
}
