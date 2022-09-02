package net.vulkanmc.vulkan;

import net.minestom.server.MinecraftServer;

public class Vulkan {

    private static Vulkan instance;
    private final MinecraftServer minecraftServer = MinecraftServer.init();

    public static void main(String[] args) {
        instance = new Vulkan();
        getInstance().startVulkan();
    }

    public void startVulkan() {
        minecraftServer.start("0.0.0.0", 25565);
    }

    public static Vulkan getInstance() {
        return instance;
    }
}
