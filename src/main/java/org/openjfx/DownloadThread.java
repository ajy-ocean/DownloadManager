package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openjfx.models.FileInfo;

public class DownloadThread extends Thread {

    private FileInfo file;
    DownloadManager manager;

    public DownloadThread(FileInfo file, DownloadManager manager) {
        this.file = file;
        this.manager = manager;
    }

    @Override
    public void run() {
        this.file.setStatus("DOWNLOADING");
        this.manager.updateUI(this.file);

        // Logic of downloading

        try {
            Files.copy(new URL(this.file.getUrl()).openStream(), Paths.get(this.file.getPath()));
            this.file.setStatus("DONE");
        } catch (IOException e) {
            this.file.setStatus("FAILDED");
            System.out.println("Downloading errorr.....");
            e.printStackTrace();
        }
        this.manager.updateUI(this.file);

    }

}
