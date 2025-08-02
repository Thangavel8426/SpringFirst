package com.lablogbook.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageService {

    private final Path root = Paths.get("uploads").toAbsolutePath().normalize();

    public void init() {
        try {
            Files.createDirectories(root);
            System.out.println("Uploads directory created/verified at: " + root.toAbsolutePath());
            System.out.println("Directory exists: " + Files.exists(root));
            System.out.println("Directory is writable: " + Files.isWritable(root));
        } catch (IOException e) {
            System.err.println("Error creating uploads directory: " + e.getMessage());
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void save(MultipartFile file, String filename) {
        try {
            System.out.println("StorageService: Saving file '" + filename + "' to " + root.toAbsolutePath());
            System.out.println("StorageService: File size: " + file.getSize() + " bytes");
            
            // Ensure directory exists
            if (!Files.exists(root)) {
                Files.createDirectories(root);
                System.out.println("StorageService: Created uploads directory");
            }
            
            Path filePath = this.root.resolve(filename);
            System.out.println("StorageService: Full file path: " + filePath.toAbsolutePath());
            
            // Check if parent directory exists
            Path parentDir = filePath.getParent();
            if (!Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("StorageService: Created parent directory: " + parentDir);
            }
            
            // Delete existing file if it exists
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("StorageService: Deleted existing file");
            }
            
            // Copy the file
            Files.copy(file.getInputStream(), filePath);
            System.out.println("StorageService: File saved successfully");
            
            // Verify the file was saved
            if (Files.exists(filePath)) {
                long fileSize = Files.size(filePath);
                System.out.println("StorageService: File verified, size: " + fileSize + " bytes");
            } else {
                throw new RuntimeException("File was not saved properly");
            }
        } catch (Exception e) {
            System.err.println("StorageService: Error saving file: " + e.getMessage());
            System.err.println("StorageService: Error type: " + e.getClass().getSimpleName());
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            System.out.println("StorageService: Loading file '" + filename + "' from " + root.toAbsolutePath());
            Path file = root.resolve(filename);
            System.out.println("StorageService: Full path: " + file.toAbsolutePath());
            
            Resource resource = new UrlResource(file.toUri());
            System.out.println("StorageService: Resource exists: " + resource.exists());
            System.out.println("StorageService: Resource is readable: " + resource.isReadable());

            if (resource.exists() || resource.isReadable()) {
                System.out.println("StorageService: File loaded successfully");
                return resource;
            } else {
                System.out.println("StorageService: File not found or not readable");
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            System.err.println("StorageService: Malformed URL error: " + e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
} 