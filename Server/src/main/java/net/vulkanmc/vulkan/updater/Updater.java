package net.vulkanmc.vulkan.updater;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.vulkanmc.vulkan.Vulkan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Scanner;

public class Updater {

    private final static Logger LOGGER = LoggerFactory.getLogger(Updater.class);

    public static void check() {
        LOGGER.info("Checking for updates...");
        try {
            String apiValue = new Scanner(new URL("https://api.github.com/repos/VulkanMC/Vulkan/releases/latest")
                    .openStream(), "UTF-8").useDelimiter("\\A").next();
            JsonObject jsonObject = JsonParser.parseString(apiValue).getAsJsonObject();
            if (!jsonObject.get("tag_name").getAsString().equals(Vulkan.getInstance().getVersion())) {
                LOGGER.warn("New version available: " + jsonObject.get("tag_name").getAsString());
            } else {
                LOGGER.info("You're up to date.");
            }
        } catch (Exception e) {
            LOGGER.error("Could not check for updates.");
        }
    }
}
