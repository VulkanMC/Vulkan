package net.vulkanmc.vulkan.updater;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.vulkanmc.vulkan.Vulkan;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Scanner;

public class Updater {

    public static void check() {
        try {
            String apiValue = new Scanner(new URL("https://api.github.com/repos/VulkanMC/Vulkan/releases/latest")
                            .openStream(), "UTF-8").useDelimiter("\\A").next();
            JsonObject jsonObject = JsonParser.parseString(apiValue).getAsJsonObject();
            if(!jsonObject.get("tag_name").getAsString().equals(Vulkan.getInstance().getVersion()))
                update();
        } catch (Exception e) {
            System.out.println("Could not check for updates.");
        }
    }

    private static void update() {
        try {
            String apiValue = new Scanner(new URL("https://api.github.com/repos/VulkanMC/Vulkan/releases/latest")
                            .openStream(), "UTF-8").useDelimiter("\\A").next();
            JsonObject jsonObject = JsonParser.parseString(apiValue).getAsJsonObject();
            JsonObject assets = jsonObject.get("assets").getAsJsonArray().get(0).getAsJsonObject();
            String downloadUrl = assets.get("browser_download_url").getAsString();
            String fileName = assets.get("name").getAsString();

            try (BufferedInputStream in = new BufferedInputStream(new URL(downloadUrl).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream("Vulkan.jar")) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    //figure out percentage downloaded
                    int percentage = (int) (((double) bytesRead / (double) in.available()) * 100);
                    System.out.println(percentage);
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                // handle exception
            }
            //new ProcessBuilder("java", "-jar", fileName).start();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Could not update.");
        }
    }
}
