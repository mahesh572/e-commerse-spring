package com.mayuktha.products.s3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectAclRequest;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class AwsS3Service {

    private final String bucketName = "e-commerse-mahesh";

    @Autowired
    S3Client s3Client;
    
    
    /*
    public void uploadFile(String keyName, String filePath)
	{
		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(keyName).build();
		s3Client.putObject(putObjectRequest, RequestBody.fromFile(Paths.get(filePath)));
	}
    */
    
    
    public String saveImageToS3(MultipartFile photo){
        try {
            String s3FileName = photo.getOriginalFilename();
            String key = photo.getOriginalFilename();
          //  String filePath = "c:/tmp/" + key; // Save temporarily

          //  photo.transferTo(new java.io.File(filePath)); // Convert MultipartFile to File
            //create aes credentials using the access and secrete key
          //  BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3SecreteKey);

            //create an s3 client with config credentials and region
            /*
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion(Regions.AP_SOUTH_1)
                    .build();
             */
            //get input stream from photo
            
            InputStream inputStream = photo.getInputStream();

          //  set metedata for the object
          //  ObjectMetadata metadata = new ObjectMetadata();
          //  metadata.setContentType(photo.getContentType());

            //create a put request to upload the image to s3
           // PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, inputStream, metadata);
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(s3FileName).build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream,photo.getSize()));

            return "https://" + bucketName + ".s3.ap-south-1.amazonaws.com/" + s3FileName;

        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to upload image to s3 bucket: " + e.getMessage());
        }
    }
}