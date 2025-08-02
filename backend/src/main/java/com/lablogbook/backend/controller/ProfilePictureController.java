package com.lablogbook.backend.controller;

import com.lablogbook.backend.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/profile-pictures")
public class ProfilePictureController {

    private final StorageService storageService;

    public ProfilePictureController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testUploadsDirectory() {
        try {
            Path uploadsPath = Paths.get("uploads");
            boolean exists = Files.exists(uploadsPath);
            boolean isDirectory = Files.isDirectory(uploadsPath);
            String absolutePath = uploadsPath.toAbsolutePath().toString();
            
            StringBuilder result = new StringBuilder();
            result.append(String.format(
                "Uploads directory test:\n" +
                "Path: %s\n" +
                "Exists: %s\n" +
                "Is Directory: %s\n" +
                "Can Write: %s\n\n",
                absolutePath, exists, isDirectory, Files.isWritable(uploadsPath)
            ));
            
            // List all files in the uploads directory
            if (exists && isDirectory) {
                result.append("Files in uploads directory:\n");
                try {
                    Files.list(uploadsPath).forEach(file -> {
                        result.append("- ").append(file.getFileName()).append(" (")
                              .append(file.toFile().length()).append(" bytes)\n");
                    });
                } catch (IOException e) {
                    result.append("Error listing files: ").append(e.getMessage());
                }
            }
            
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error testing uploads directory: " + e.getMessage());
        }
    }

    @GetMapping("/config")
    public ResponseEntity<String> testMultipartConfig() {
        try {
            StringBuilder result = new StringBuilder();
            result.append("Multipart Configuration Test:\n\n");
            
            // Test if we can create a temporary file
            Path tempFile = Files.createTempFile("test", ".txt");
            result.append("Can create temp file: true\n");
            result.append("Temp file path: ").append(tempFile.toAbsolutePath()).append("\n");
            
            // Clean up
            Files.deleteIfExists(tempFile);
            
            result.append("\nBackend is ready for file uploads.\n");
            result.append("Max file size should be 100MB based on application.properties.\n");
            
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error testing multipart config: " + e.getMessage());
        }
    }

    @PostMapping("/{username}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable String username, @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("=== PROFILE PICTURE UPLOAD START ===");
            System.out.println("Uploading profile picture for user: " + username);
            System.out.println("Original filename: " + file.getOriginalFilename());
            System.out.println("File size: " + file.getSize() + " bytes");
            System.out.println("Content type: " + file.getContentType());
            System.out.println("Is file empty: " + file.isEmpty());
            
            // Validate file
            if (file.isEmpty()) {
                System.err.println("ERROR: File is empty");
                return ResponseEntity.badRequest().body("{\"message\": \"File is empty\"}");
            }
            
            if (file.getSize() == 0) {
                System.err.println("ERROR: File size is 0");
                return ResponseEntity.badRequest().body("{\"message\": \"File size is 0\"}");
            }
            
            // Get original file extension
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String filenameWithExt = username + extension;
            System.out.println("Saving file as: " + filenameWithExt);
            
            storageService.save(file, filenameWithExt);
            System.out.println("File saved successfully");
            System.out.println("=== PROFILE PICTURE UPLOAD SUCCESS ===");
            return ResponseEntity.ok().body("{\"message\": \"File uploaded successfully\"}");
        } catch (Exception e) {
            System.err.println("=== PROFILE PICTURE UPLOAD ERROR ===");
            System.err.println("Error uploading file: " + e.getMessage());
            System.err.println("Error type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("{\"message\": \"Could not upload the file: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Resource> getProfilePicture(@PathVariable String username) {
        System.out.println("Requesting profile picture for user: " + username);
        
        // Try to find a file with common image extensions
        String[] exts = {".jpg", ".jpeg", ".png", ".gif", ""};
        Resource file = null;
        String foundExt = null;
        
        for (String ext : exts) {
            String filename = username + ext;
            System.out.println("Trying to load: " + filename);
            file = storageService.load(filename);
            if (file != null && file.exists() && file.isReadable()) {
                foundExt = ext;
                System.out.println("Found user-specific file: " + filename);
                break;
            } else {
                System.out.println("File not found or not readable: " + filename);
            }
        }
        
        // If no user profile picture found, return 404 (frontend will handle default)
        if (file == null || !file.exists() || !file.isReadable()) {
            System.out.println("No user-specific profile picture found for user: " + username);
            return ResponseEntity.notFound().build();
        }
        
        // Set content type based on extension
        String contentType = MediaType.IMAGE_JPEG_VALUE;
        if (".png".equalsIgnoreCase(foundExt)) contentType = MediaType.IMAGE_PNG_VALUE;
        else if (".gif".equalsIgnoreCase(foundExt)) contentType = MediaType.IMAGE_GIF_VALUE;
        
        System.out.println("Serving user-specific file with content type: " + contentType);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(file);
    }
} 