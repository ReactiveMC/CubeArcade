package com.cubemc.targeted;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.game.GameState;
import com.cubemc.api.game.events.UpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class EndChecker implements Listener {

    @EventHandler
    public void onUpdate(UpdateEvent e){
        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME) return;

        if (CubeAPI.getGameManager().getTeamManager().getTeam("Players").getMembers().size()==0){
            CubeAPI.getGameManager().displayWinner(null, "Something went wrong!");
            CubeAPI.getGameManager().endGame();
            return;
        }

        if (CubeAPI.getGameManager().getTeamManager().getTeam("Players").getMembers().size()==1){
            CubeAPI.getGameManager().displayWinner(new String[]{Bukkit.getPlayer(CubeAPI.getGameManager().getTeamManager().getTeam("Players").getMembers().get(0)).getDisplayName() });
            CubeAPI.getGameManager().endGame();
            return;
        }
    }

}
