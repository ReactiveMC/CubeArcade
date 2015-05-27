package com.cubemc.targeted.Listeners;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
import com.cubemc.targeted.Targeted;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 27/05/2015
 */
public class Death implements Listener {

    @EventHandler
    public void onPlayer(PlayerDeathEvent e){
        e.setDeathMessage(null);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        final Player p = e.getPlayer();
        Bukkit.getScheduler().scheduleSyncDelayedTask(CubeAPI.getPlugin(), new Runnable() {
            @Override
            public void run() {
                CubeAPI.getGameManager().getLobbyManager().sendPlayerToLobby(p);
            }
        }, 1L);

        CubeAPI.getGameManager().getSpectateManager().makePlayer(p);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();
        Player k = p.getKiller();

        Bukkit.broadcastMessage(M.reg(k.getDisplayName() + " §7eliminated " + p.getDisplayName() + "§7."));

        Targeted.getTargetManager().stealTargetFromPlayer(k, p);
    }

}
