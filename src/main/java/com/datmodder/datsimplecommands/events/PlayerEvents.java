package com.datmodder.datsimplecommands.events;

import com.datmodder.datsimplecommands.SimpleCommands;
import com.datmodder.datsimplecommands.utils.PlayerManager;
import com.datmodder.datsimplecommands.utils.structures.PlayerData;
import com.demmodders.datmoddingapi.structures.Location;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.UUID;

@Mod.EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent e) {
        UUID playerID = e.player.getUniqueID();
        PlayerManager pMan = PlayerManager.getInstance();
        pMan.loadPlayer(playerID);
    }

    @SubscribeEvent
    public static void playerLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        UUID playerID = e.player.getUniqueID();
        PlayerManager pMan = PlayerManager.getInstance();
        pMan.unloadPlayer(playerID);
    }

    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent e) {
        if (e.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) e.getEntity();
            PlayerManager.getInstance().updatePlayerBackLocation(player.getUniqueID(), new Location(player.dimension, player.posX, player.posY, player.posZ, player.rotationPitch, player.rotationYaw));
        }
    }
}
