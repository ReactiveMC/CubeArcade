package com.cubemc.cubepvp.Listeners;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
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
    public void onEntity(EntityDeathEvent e){
        if (!(e.getEntity() instanceof Player)){
            return;
        }
        Player p = (Player) e.getEntity();

        if (p.getKiller() != null){
            Bukkit.broadcastMessage(M.reg(p.getDisplayName() + " §7was killed by " + p.getKiller().getDisplayName() + "§7."));
        }else{
            Bukkit.broadcastMessage(M.reg(p.getDisplayName() + " §7died."));
        }

        CubeAPI.getGameManager().getTeamManager().leaveTeam(p);

        if (CubeAPI.getGameManager().getTeamManager().getTeam("Red").getMembers().size()==0){
            //Blue has won then.
            CubeAPI.getGameManager().displayWinner(CubeAPI.getGameManager().getTeamManager().getTeam("Blue"), null);
            CubeAPI.getGameManager().endGame();
        }else if (CubeAPI.getGameManager().getTeamManager().getTeam("Blue").getMembers().size()==0){
            //Red has won then.
            CubeAPI.getGameManager().displayWinner(CubeAPI.getGameManager().getTeamManager().getTeam("Red"), null);
            CubeAPI.getGameManager().endGame();
        }
    }

}
