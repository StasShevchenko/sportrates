package com.example.sportrates.conroller;

import com.example.sportrates.db_model.Image;
import com.example.sportrates.db_model.User;
import com.example.sportrates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/uploadimage")
    @CrossOrigin(origins = "*")
    public void uploadImage(
            @RequestBody MultipartFile image,
            @RequestParam(name = "userId") Long userId
    ) throws IOException {
        Image avatar = toImageEntity(image);
        User user = userRepository.findById(userId).get();
        user.setImage(avatar);
        userRepository.save(user);
    }

    @GetMapping("/loadimage")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> loadImage(
            @RequestParam(name = "userId") Long userId
    ){
        User user = userRepository.findById(userId).get();
        Image image = user.getImage();
        if(image != null) {
            return ResponseEntity.ok()
                    .header("fileName", image.getOriginalFileName())
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .contentLength(image.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
