package net.vulkanmc.vulkan;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.vulkanmc.vulkan.listener.ItemDropping;
import net.vulkanmc.vulkan.listener.ItemPickup;

public class Vulkan {

    private static Vulkan instance;
    private final MinecraftServer minecraftServer = MinecraftServer.init();
    private final GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

    public static void main(String[] args) {
        instance = new Vulkan();
        //TODO: Make jar not shaded, instead code a library downloader.
        getInstance().startVulkan();
    }

    private void startVulkan() {
        ItemDropping.register(globalEventHandler);
        ItemPickup.register(globalEventHandler);
        minecraftServer.start("0.0.0.0", 25565);
    }

    public static Vulkan getInstance() {
        return instance;
    }
}
