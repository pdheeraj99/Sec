package com.downloader;

import java.util.Random;

/**
 * 🎯 Ee class oka file download ni simulate chestundi.
 * Idi Runnable, ante deenini oka separate thread lo run cheyochu.
 */
public class FileDownloader implements Runnable {
    private final String fileName;
    private final Random random = new Random();

    public FileDownloader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("🔽 [" + fileName + "] Download started by " + Thread.currentThread().getName());
        try {
            int totalBytes = 1000;
            for (int downloadedBytes = 0; downloadedBytes <= totalBytes; downloadedBytes += 200) {
                // Simulate network delay
                Thread.sleep(random.nextInt(1000)); // Sleep for up to 1 second

                double percentage = ((double) downloadedBytes / totalBytes) * 100;
                System.out.printf("[%s] Progress: %.0f%%\n", fileName, percentage);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("❌ [" + fileName + "] Download was interrupted.");
            return;
        }
        System.out.println("✅ [" + fileName + "] Download finished successfully.");
    }
}
