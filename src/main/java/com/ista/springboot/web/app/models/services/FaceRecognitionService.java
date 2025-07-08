package com.ista.springboot.web.app.models.services;



import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.nio.ByteBuffer;

@Service
public class FaceRecognitionService {

    private final RekognitionClient rekognitionClient;

    public FaceRecognitionService(RekognitionClient rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    public boolean compararRostros(byte[] imagen1, byte[] imagen2) {
        Image source = Image.builder()
                .bytes(SdkBytes.fromByteBuffer(ByteBuffer.wrap(imagen1)))
                .build();

        Image target = Image.builder()
                .bytes(SdkBytes.fromByteBuffer(ByteBuffer.wrap(imagen2)))
                .build();

        CompareFacesRequest request = CompareFacesRequest.builder()
                .sourceImage(source)
                .targetImage(target)
                .similarityThreshold(90F)
                .build();

        try {
            CompareFacesResponse result = rekognitionClient.compareFaces(request);
            return !result.faceMatches().isEmpty();
        } catch (RekognitionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
