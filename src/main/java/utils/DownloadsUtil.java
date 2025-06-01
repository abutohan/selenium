package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static utils.ReadProperties.loadProperty;

public class DownloadsUtil {

    /**
     * Clears all files from the designated download directory.
     *
     * @throws IOException If an I/O error occurs during deletion.
     */
    public static void clearDownloadDirectory(String downloadPath) throws IOException {

        File downloadDir = new File(downloadPath);
        if (downloadDir.exists() && downloadDir.isDirectory()) {
            try (Stream<Path> walk = Files.walk(downloadDir.toPath())) {
                walk.filter(Files::isRegularFile).map(Path::toFile).forEach(File::delete);
            }
            System.out.println("Download directory '" + downloadPath + "' cleared.");
        } else if (!downloadDir.exists()) {
            downloadDir.mkdirs(); // Recreate if it was deleted
        }
    }

    /**
     * Waits for a specific file to appear and finish downloading in the download directory.
     * Checks for the final file name and waits for its size to stabilize.
     *
     * @param fileName       The expected name of the downloaded file.
     * @param timeoutSeconds The maximum time to wait for the download to complete, in seconds.
     * @return true if the download completes successfully, false otherwise.
     * @throws InterruptedException If the thread is interrupted during waiting.
     */
    public static boolean waitForDownloadCompletion(String fileName, String downloadPath, int timeoutSeconds) throws InterruptedException, IOException {

        File downloadedFile = new File(downloadPath, fileName);
        long startTime = System.currentTimeMillis();
        long timeoutMillis = timeoutSeconds * 1000L;
        long previousSize = -1;
        int stableChecks = 0;
        int maxStableChecks = 3; // File size must be stable for 3 consecutive checks (3 seconds)

        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            if (downloadedFile.exists()) {
                long currentSize = downloadedFile.length();

                // Check for temporary download extensions (Chrome specific)
                if (downloadedFile.getName().endsWith(".crdownload")) {
                    System.out.println("  File still downloading (.crdownload): " + currentSize + " bytes");
                    previousSize = -1; // Reset stability check if still a temporary file
                    stableChecks = 0;
                } else if (currentSize > 0) { // File exists and has some content
                    if (currentSize == previousSize) {
                        stableChecks++;
                        System.out.println("  File size stable: " + currentSize + " bytes (check " + stableChecks + "/" + maxStableChecks + ")");
                        if (stableChecks >= maxStableChecks) {
                            System.out.println("  Download of '" + fileName + "' complete. Final size: " + currentSize + " bytes.");
                            return true; // File size has been stable for long enough
                        }
                    } else {
                        System.out.println("  Downloading: " + currentSize + " bytes (size changed)");
                        previousSize = currentSize;
                        stableChecks = 0; // Reset stable checks if size changed
                    }
                } else {
                    System.out.println("  File exists but size is 0 bytes (waiting for content)...");
                    previousSize = 0;
                    stableChecks = 0;
                }
            } else {
                System.out.println("  Waiting for file '" + fileName + "' to appear in '" + downloadPath + "'...");
                previousSize = -1; // Reset stability if file temporarily disappears or not yet created
                stableChecks = 0;
            }
            Thread.sleep(1000); // Check every 1 second
        }

        System.err.println("Timeout: Download of '" + fileName + "' did not complete within " + timeoutSeconds + " seconds.");
        return false;
    }

    public static String absoluteDownloadPath(String propertyKey) throws IOException {
        String relativeDownloadPath = loadProperty().getProperty(propertyKey);

        if (relativeDownloadPath == null || relativeDownloadPath.isBlank())
            throw new IllegalArgumentException(String.format("Property not found: %s", propertyKey));

        return Paths.get(relativeDownloadPath).toAbsolutePath().toString();
    }
}
