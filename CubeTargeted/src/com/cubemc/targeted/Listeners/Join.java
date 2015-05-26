package com.cubemc.targeted.Listeners;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if (CubeAPI.getGameManager().getGame().getState() != GameState.WAITING) return;
        if (CubeAPI.getGameManager().getGame().getMinPlayers() == Bukkit.getOnlinePlayers().size()){
            CubeAPI.getGameManager().beginCountdownSequence();
        }
    }

}
