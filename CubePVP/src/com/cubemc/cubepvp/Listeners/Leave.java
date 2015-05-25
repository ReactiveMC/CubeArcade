package com.cubemc.cubepvp.Listeners;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Game.GameState;
import com.cubemc.api.Utils.M;
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
 * 24/05/2015
 */
public class Leave implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME) return;

        if (CubeAPI.getGameManager().getTeamManager().hasTeam(p)==false){
            Bukkit.broadcastMessage(M.fatal("He had no team :o"));
        }

        if (CubeAPI.getGameManager().getTeamManager().getTeam(p).getName().equals("Red") || CubeAPI.getGameManager().getTeamManager().getTeam(p).getName().equals("Blue")){

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

}
