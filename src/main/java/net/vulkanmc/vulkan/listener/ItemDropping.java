package net.vulkanmc.vulkan.listener;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.time.TimeUnit;

import java.time.Duration;

public class ItemDropping {

    public static void register(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(ItemDropEvent.class, event -> {
            final Player player = event.getPlayer();
            ItemStack droppedItem = event.getItemStack();
            Pos playerPos = player.getPosition();
            //Create new item entity of dropped item.
            ItemEntity itemEntity = new ItemEntity(droppedItem);
            itemEntity.setPickupDelay(Duration.of(500, TimeUnit.MILLISECOND));
            //Set item entity position to player position y + 1.5.
            itemEntity.setInstance(player.getInstance(), playerPos.withY(y -> y + 1.5));
            Vec velocity = playerPos.direction().mul(6);
            //Set item entity velocity to player looking direction * 6.
            itemEntity.setVelocity(velocity);
        });
    }
}
