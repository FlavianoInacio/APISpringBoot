package com.example.carros.api;

import com.example.carros.entidades.UploadInput;
import com.example.carros.entidades.UploadOutput;
import com.example.carros.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping
    public ResponseEntity upload(@RequestBody UploadInput uploadInput) throws IOException {
        String url = firebaseService.upload(uploadInput);
        return ResponseEntity.ok(new UploadOutput(url));
    }
}
