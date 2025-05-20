package com.leetcode.ocp.api;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private Map<String, SoftReference<byte[]>> cache = new HashMap<>();

    public byte[] getImageData(String imagePath) {
        SoftReference<byte[]> softRef = cache.get(imagePath);

        if (softRef != null) {
            byte[] imageData = softRef.get();
            if (imageData != null) {
                System.out.println("Cache hit for: " + imagePath);
                return imageData;
            }
            // Softly referenced object was collected, remove the entry
            cache.remove(imagePath);
        }
        // Load image data (expensive operation)
        byte[] imageData = loadImageFromDisk(imagePath);
        cache.put(imagePath, new SoftReference<>(imageData));
        System.out.println("Cache miss for: " + imagePath);
        return imageData;
    }

    private byte[] loadImageFromDisk(String path) {
        // Simulate loading from disk
        System.out.println("Loading image from disk: " + path);
        return new byte[1024 * 1024]; // Simulate 1MB image
    }

    public static void main(String[] args) {
        ImageCache cache = new ImageCache();
        cache.getImageData("image1.png");
        cache.getImageData("image1.png"); // Cache hit

        // Simulate memory pressure (you'd usually do this by allocating lots of other objects)
        System.out.println("Simulating memory pressure...");
        for (int i = 0; i < 10; i++) {

            byte[] b = new byte[10 * 1024 * 1024];// Allocate 10MB each time
        }

        // The softly referenced image data might be collected now
        cache.getImageData("image1.png"); // Might be a cache miss, requiring reload
    }
}
