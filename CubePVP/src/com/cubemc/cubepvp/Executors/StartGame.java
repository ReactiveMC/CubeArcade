package com.cubemc.cubepvp.Executors;


import com.cubemc.api.CubeAPI;
import com.cubemc.api.Game.executors.StartGameExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class StartGame implements StartGameExecutor {

    @Override
    public void startWarmup() {
        for (String s : CubeAPI.getGameManager().getTeamManager().getTeam("Red").getMembers()){
            Player p = Bukkit.getPlayer(s);

            //Teleport players, give them kits etc.
        }
    }

    @Override
    public void startGame() {

    }
}
