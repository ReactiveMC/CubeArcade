package com.cubemc.cubepvp.Executors;


import com.cubemc.api.CubeAPI;
import com.cubemc.api.Game.executors.StartGameExecutor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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

        String currentTeam = "Red";
        for (Player p : Bukkit.getOnlinePlayers()){
            if (currentTeam.equals("Red")){
                CubeAPI.getGameManager().getTeamManager().joinTeam(p, CubeAPI.getGameManager().getTeamManager().getTeam("Red"));
                currentTeam = "Blue";
            }else{
                CubeAPI.getGameManager().getTeamManager().joinTeam(p, CubeAPI.getGameManager().getTeamManager().getTeam("Blue"));
                currentTeam = "Red";
            }
        }

        for (String s : CubeAPI.getGameManager().getTeamManager().getTeam("Red").getMembers()){
            Player p = Bukkit.getPlayer(s);

            p.teleport(new Location(Bukkit.getWorld(CubeAPI.getGameManager().getGame().getCurrentMap().getFolderName()), 0, 65, 10));
        }
        for (String s : CubeAPI.getGameManager().getTeamManager().getTeam("Blue").getMembers()){
            Player p = Bukkit.getPlayer(s);

            p.teleport(new Location(Bukkit.getWorld(CubeAPI.getGameManager().getGame().getCurrentMap().getFolderName()), 0, 65, -10));
        }

        for (Player p : Bukkit.getOnlinePlayers()){
            p.getInventory().clear();
            CubeAPI.getGameManager().getKitManager().getSelectedKit(p).applyKit(p);
        }
    }

    @Override
    public void startGame() {
        CubeAPI.getGameManager().getGame().getSet().setCanPVP(true);
        CubeAPI.getGameManager().getGame().getSet().setCanPlayersTakeDamage(true);
        CubeAPI.getGameManager().getGame().getSet().setCanPVE(true);
    }
}
