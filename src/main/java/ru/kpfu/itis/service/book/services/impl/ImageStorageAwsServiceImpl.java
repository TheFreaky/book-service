package ru.kpfu.itis.service.book.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.service.book.services.ImageStorageService;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
public class ImageStorageAwsServiceImpl implements ImageStorageService {

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    @Value("${cloud.aws.bucket.url}")
    private String bucketUrl;

    private final AmazonS3 amazonS3Client;

    @Autowired
    public ImageStorageAwsServiceImpl(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("File is empty or not selected");
        }

        String uniqueFileName = generateUniqueFilename(multipartFile.getOriginalFilename());
        PutObjectRequest putObjectRequest = createPutObjectRequest(uniqueFileName, multipartFile, bucketName);
        uploadFileToS3(putObjectRequest);
        return bucketUrl + uniqueFileName;
    }

    private void uploadFileToS3(PutObjectRequest request) {
        amazonS3Client.putObject(request);
    }

    @SneakyThrows
    private PutObjectRequest createPutObjectRequest(String fileName, MultipartFile multipartFile, String bucketName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());
        return new PutObjectRequest(bucketName, fileName, multipartFile.getInputStream(), metadata);
    }

    //ToDo create smth like interface ObjectNameGenerationStrategy and impl DateAndUuidObjectNameGenerationStrategy
    private String generateUniqueFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");

        String name = LocalDate.now().toString() + "_" + uuid;
        String ext = Files.getFileExtension(Strings.nullToEmpty(originalFilename));
        return name + (ext.isEmpty() ? "" : "." + ext);
    }
}
