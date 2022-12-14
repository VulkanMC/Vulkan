package net.vulkanmc.vulkan.world.generator;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import org.jetbrains.annotations.NotNull;

public class NetherGenerator implements Generator {

    @Override
    public void generate(@NotNull GenerationUnit unit) {
        unit.modifier().fillHeight(0, 5, Block.NETHERRACK);
    }
}
