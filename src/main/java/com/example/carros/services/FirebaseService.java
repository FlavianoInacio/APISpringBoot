package com.example.carros.services;

import com.example.carros.entidades.UploadInput;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class FirebaseService {

    @PostConstruct
    private  void init() throws IOException {
        InputStream serviceAccount =
                getClass().getResourceAsStream("/firebaseserviceaccount.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("carros2-b4598.appspot.com")
                .build();

       FirebaseApp.initializeApp(options);
    }

    public String upload(UploadInput uploadInput){
        Bucket bucket = StorageClient.getInstance().bucket();
        byte[] bytes = Base64.getDecoder().decode(uploadInput.getBase64());
        Blob blob = bucket.create(uploadInput.getFileName(),bytes,uploadInput.getMineType());
        blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        return String.format("https://storage.googleapis.com/%s/%s",bucket.getName(),uploadInput.getFileName());
    }
}
