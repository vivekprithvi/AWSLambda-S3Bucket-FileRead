package com.amazonaws.lambda.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class LambdaFunctionHandler implements RequestHandler<String, String> {

	AmazonS3 client = new AmazonS3Client();
	String line;
	StringBuilder sBuilder = new StringBuilder();
	
    @Override
    public String handleRequest(String input, Context context) {
    	input="employee.xslt";
        context.getLogger().log("Input: " + input);
        
        S3Object xFile = client.getObject(new GetObjectRequest("test-bucket-api-postman", input));
    	InputStream contents = xFile.getObjectContent();
    	
    	BufferedReader reader = new BufferedReader(new 
        		InputStreamReader(contents));
        
    	while (true) {
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (line == null) break;

            sBuilder.append(line);
        }
    	JSONObject jsonObj = new JSONObject();

        // TODO: implement your handler
        return sBuilder.toString();
    }

}
