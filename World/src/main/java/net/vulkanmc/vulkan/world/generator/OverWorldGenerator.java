package net.vulkanmc.vulkan.world.generator;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import org.jetbrains.annotations.NotNull;

public class OverWorldGenerator implements Generator {

    @Override
    public void generate(@NotNull GenerationUnit unit) {
        unit.modifier().fillHeight(0, 1, Block.BEDROCK);
        unit.modifier().fillHeight(1, 4, Block.DIRT);
        unit.modifier().fillHeight(4, 5, Block.GRASS_BLOCK);
        final Point start = unit.absoluteStart();
        final Point size = unit.size();
        for (int x = 0; x < size.blockX(); x++) {
            for (int z = 0; z < size.blockZ(); z++) {
                int random = (Math.random() <= 0.5) ? 1 : 2;
                if (random == 1) {
                    unit.modifier().setBlock(start.add(x, 69, z), Block.GRASS);
                }
            }
        }
    }
}
