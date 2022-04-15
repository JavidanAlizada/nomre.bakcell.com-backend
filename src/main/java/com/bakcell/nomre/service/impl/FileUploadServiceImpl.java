package com.bakcell.nomre.service.impl;

import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.service.FileUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * @author Javidan Alizada
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private static final String CHARSET = "UTF-8";
    private static final String ELEMENT_DELIMITER = ",";
    private static final String LINE_DELIMITER = "\n";
    private static final String INDEX_NAME = "msisdn";
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;
    private InputStreamReader inputStreamReader;

    @SneakyThrows
    private Map<String, Object> convertPhoneNumberEntityToMap(PhoneNumberEntity entity) {
        String json = objectMapper.writeValueAsString(entity);
        return objectMapper.readValue(json, Map.class);
    }

    @Override
    @SneakyThrows
    public void upload(MultipartFile file) {
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream(), CHARSET);
        log.info("Data is processing...");
        this.saveAll(inputStreamReader);
        log.info("!!!Data Inserted To Elasticsearch database!!!");
    }

    @SneakyThrows
    public void saveAll(InputStreamReader inputStreamReader) {
        setInputStreamReader(inputStreamReader);
        List<String[]> msisdnAndCategoryList = getMSISDNAndCategoryListFromCsv();
        BulkRequest bulkRequest = Requests.bulkRequest();
        RequestOptions options = RequestOptions.DEFAULT;
        msisdnAndCategoryList.forEach((msisdnAndCategory) -> {
            PhoneNumberEntity entity = setFieldsAndGetObject(msisdnAndCategory);
            bulkRequest.add(Requests
                    .indexRequest(INDEX_NAME)
                    .source(convertPhoneNumberEntityToMap(entity)));
        });
        restHighLevelClient.bulk(bulkRequest, options);
    }


    public PhoneNumberEntity setFieldsAndGetObject(String[] msisdnAndCategory) {
        return PhoneNumberEntity.builder()
                .categoryName(msisdnAndCategory[1].substring(1, msisdnAndCategory[1].length() - 1))
                .prefix(String.format("0%s", msisdnAndCategory[0].substring(1, 3)))
                .msisdn(msisdnAndCategory[0].substring(1, msisdnAndCategory[0].length() - 1))
                .reserved(Boolean.FALSE)
                .build();
    }

    private InputStreamReader getInputStreamReader() {
        return this.inputStreamReader;
    }

    public void setInputStreamReader(InputStreamReader inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
    }

    public List<String[]> getMSISDNAndCategoryListFromCsv() {
        List<String[]> msisdnAndCategories = new ArrayList<>();
        String row;
        try (BufferedReader csvReader = new BufferedReader(getInputStreamReader())) {
            int count = 0;
            while (nonNull(row = csvReader.readLine())) {
                String[] msisdnCategoryMapLine = row.split(LINE_DELIMITER);
                for (String msisdnCategoryMap : msisdnCategoryMapLine) {
                    if (count == 0) {
                        count += 1;
                        continue;
                    }
                    String[] msisdnAndCategory = msisdnCategoryMap.split(ELEMENT_DELIMITER);
                    msisdnAndCategories.add(msisdnAndCategory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msisdnAndCategories;
    }
}
