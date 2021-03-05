package com.example.carros;

import com.example.carros.entidades.UploadInput;
import com.example.carros.entidades.UploadOutput;
import com.example.carros.services.FirebaseService;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadTest extends BaseAPITest{
    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    protected TestRestTemplate rest;

    private UploadInput getUploadInput(){
        UploadInput uploadInput = new UploadInput();
        uploadInput.setFileName("arquivo.txt");
        uploadInput.setBase64("Zmxhdmlhbm8gc2FudG9zIGluw6FjaW8=");
        uploadInput.setMineType("text/plain");
        return  uploadInput;
    }
    @Test
    public void testUploadFirebase() {
        String url = firebaseService.upload(getUploadInput());
        ResponseEntity responseEntity = rest.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUploadApi(){
        UploadInput uploadInput = getUploadInput();
        ResponseEntity<UploadOutput> response = post("/api/v1/upload", uploadInput, UploadOutput.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        UploadOutput uploadOutput = response.getBody();
        assertNotNull(uploadOutput);

        String url = uploadOutput.getUrl();

        ResponseEntity responseEntity = rest.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());



    }
}
