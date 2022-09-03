package net.vulkanmc.vulkan.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.vulkanmc.vulkan.world.generator.EndGenerator;
import net.vulkanmc.vulkan.world.generator.NetherGenerator;
import net.vulkanmc.vulkan.world.generator.OverWorldGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VanillaWorld {

    private static final InstanceManager instanceManager = MinecraftServer.getInstanceManager();
    private static InstanceContainer overWorldContainer;
    private static InstanceContainer netherContainer;
    private static InstanceContainer endContainer;

    public static void setupWorlds() {
        VanillaDimensionTypes.registerAll(MinecraftServer.getDimensionTypeManager());
        overWorldContainer = instanceManager.createInstanceContainer(VanillaDimensionTypes.OVERWORLD);
        netherContainer = instanceManager.createInstanceContainer(VanillaDimensionTypes.NETHER);
        endContainer = instanceManager.createInstanceContainer(VanillaDimensionTypes.END);
        overWorldContainer.setGenerator(new OverWorldGenerator());
        netherContainer.setGenerator(new NetherGenerator());
        endContainer.setGenerator(new EndGenerator());
    }

    public static InstanceContainer getOverWorldContainer() {
        return overWorldContainer;
    }

    public static InstanceContainer getNetherContainer() {
        return netherContainer;
    }

    public static InstanceContainer getEndContainer() {
        return endContainer;
    }
}
