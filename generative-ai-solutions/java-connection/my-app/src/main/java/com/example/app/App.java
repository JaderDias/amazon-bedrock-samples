package com.example.app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.temporal.ChronoUnit;

import com.example.app.pojo.CohereResponse;
import com.example.app.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;


public class App {
    public static void main(String[] args) {
        try {
            String BEDROCK_JSON_BODY = "{\"prompt\":\"Tell me a funny story\",\"max_tokens\":400,\"temperature\":0.75,\"p\":0.01,\"k\":0,\"stop_sequences\":[],\"return_likelihoods\":\"NONE\"}";

            // send prompt to bedrock
            String awsRegion = "us-east-1";

            Utils utils = new Utils();
            utils.listFoundationModels(awsRegion);

            BedrockRuntimeClient bedrockClient = BedrockRuntimeClient.builder()
                .region(Region.of(awsRegion))
                .httpClient(
                    ApacheHttpClient.builder()
                    .socketTimeout(Duration.of(2, ChronoUnit.MINUTES))
                    .build())
                    .build();
            InvokeModelResponse invokeModel = bedrockClient
                .invokeModel(InvokeModelRequest.builder()
                .modelId("cohere.command-text-v14")
                .body(SdkBytes.fromString(BEDROCK_JSON_BODY, Charset.defaultCharset()))
                .build());


             ObjectMapper mapper = new ObjectMapper();
             CohereResponse claudeResponse = mapper.readValue(invokeModel.body().asUtf8String(), CohereResponse.class);

            System.out.println(claudeResponse.getGenerations()[0].getText());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}