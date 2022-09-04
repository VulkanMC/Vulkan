package net.vulkanmc.vulkan;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.vulkanmc.vulkan.config.Config;
import net.vulkanmc.vulkan.listener.ItemDropping;
import net.vulkanmc.vulkan.listener.ItemPickup;
import net.vulkanmc.vulkan.updater.Updater;
import net.vulkanmc.vulkan.world.VanillaWorld;

public class Vulkan {

    private static Vulkan instance;
    private final String name = "Vulkan";
    private final String version = "v1.0.0";

    private final MinecraftServer minecraftServer = MinecraftServer.init();
    private final GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

    public static void main(String[] args) {
        instance = new Vulkan();
        Updater.check();
        Config.copy();
        getInstance().printInfo();
        getInstance().startVulkan();
    }

    private void startVulkan() {
        ItemDropping.register(globalEventHandler);
        ItemPickup.register(globalEventHandler);
        MinecraftServer.setBrandName(name);
        VanillaWorld.setupWorlds();
        minecraftServer.start("0.0.0.0", 25565);
    }

    public static Vulkan getInstance() {
        return instance;
    }

    private void printInfo() {
        System.out.println("#######################################");
        System.out.println("Vulkan info:");
        System.out.println("Version: " + version);
        System.out.println("Release type: "/*TODO: Actual release type system.*/ + "Alpha");
        System.out.println("#######################################");
        System.out.println("System information:");
        System.out.println("Java: " + System.getProperty("java.vendor") + " " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch"));
        System.out.println("Available Cores: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Available Memory: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
        System.out.println("#######################################");
    }

    public String getVersion() {
        return version;
    }
}
