package net.vulkanmc.vulkan.listener;

import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.PickupItemEvent;

public class ItemPickup {

    public static void register(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(PickupItemEvent.class, event -> {
            //Check if entity is not a player.
            if(!(event.getEntity() instanceof Player)) { event.setCancelled(true); return; }
            Player player = (Player) event.getEntity();
            //Try to add item to inventory. If it fails (no space), cancel the event.
            if(!player.getInventory().addItemStack(event.getItemStack())) { event.setCancelled(true); }
        });
    }
}
