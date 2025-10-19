package com.downloader;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 🎯 FileDownloader class kosam simple unit test.
 *
 * NOTE: Multithreaded code ni unit test cheyadam konchem complex.
 * Manam timers, latches, or special libraries (like Awaitility) vaadali.
 * Ippatiki, manam oka basic test rastunnam to ensure our task runs without errors.
 */
class FileDownloaderTest {

    @Test
    void testFileDownloaderRuns() {
        // Create an instance of our Runnable
        FileDownloader downloader = new FileDownloader("test-file.zip");

        // Create a thread with our task
        Thread testThread = new Thread(downloader);

        // Use an UncaughtExceptionHandler to catch any exceptions from the thread
        final var exceptionHolder = new Object(){ Throwable e = null; };
        testThread.setUncaughtExceptionHandler((thread, throwable) -> {
            exceptionHolder.e = throwable;
        });

        // Start the thread
        testThread.start();

        try {
            // Wait for the thread to complete (with a timeout to prevent infinite wait)
            testThread.join(10000); // 10 seconds timeout
        } catch (InterruptedException e) {
            fail("Test was interrupted.");
        }

        // Assert that no exception was thrown from the thread.
        // Ee test pass aithe, mana 'run' method lo unhandled exceptions raledu ani ardam.
        assertNull(exceptionHolder.e, "The FileDownloader thread threw an unexpected exception.");
    }
}
