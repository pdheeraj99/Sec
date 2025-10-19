package com.downloader;

import java.util.ArrayList;
import java.util.List;

/**
 * 🎯 Ee class antha download process ni manage chestundi.
 * Multiple files ni concurrently download cheyadaniki threads ni create chestundi.
 */
public class DownloadManager {
    public static void main(String[] args) {
        // Step 1: Manam download cheyalsina files list.
        List<String> filesToDownload = List.of(
            "document.pdf",
            "image.jpg",
            "song.mp3",
            "video.mp4"
        );

        System.out.println("🚀 Download Manager started. Preparing to download " + filesToDownload.size() + " files.");

        List<Thread> downloadThreads = new ArrayList<>();

        // Step 2: Prathi file kosam oka kotha thread ni create cheddam.
        for (String fileName : filesToDownload) {
            // Create a task for each file
            FileDownloader downloaderTask = new FileDownloader(fileName);
            // Create a new thread for the task
            Thread downloaderThread = new Thread(downloaderTask);
            // Give a meaningful name to the thread
            downloaderThread.setName("Downloader-" + fileName);

            downloadThreads.add(downloaderThread);

            // Start the download in a new thread
            downloaderThread.start();
        }

        // Step 3: Anni download threads complete ayyevaraku main thread wait cheyali.
        // Idi cheyakapothe, main thread mundare finish aipotundi.
        for (Thread thread : downloadThreads) {
            try {
                thread.join(); // 'join' method gurinchi next section lo detailed ga chuddam.
                               // Ippatiki, idi ee thread aipoyenta varaku wait chestundi ani anuko.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Main thread was interrupted while waiting for downloads to complete.");
            }
        }

        System.out.println("🏁 All downloads are complete. Download Manager shutting down.");
    }
}
