package com.cubemc.targeted.Listeners;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
import com.cubemc.api.game.GameState;
import com.cubemc.targeted.Targeted;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 27/05/2015
 */
public class Leave implements Listener {

    @EventHandler
    public void leave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage(null);

        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME) return;

        if (!(CubeAPI.getGameManager().getTeamManager().getTeam(p).getName().equalsIgnoreCase("Players"))) return;

        Bukkit.broadcastMessage(M.reg(p.getDisplayName() + " §7has left and been eliminated."));

        if (Targeted.getTargetManager().getAttacker(p) != null){
            Targeted.getTargetManager().stealTargetFromPlayer(Targeted.getTargetManager().getAttacker(p), p);
        }
    }

}
