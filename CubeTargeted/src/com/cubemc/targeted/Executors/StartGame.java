package com.cubemc.targeted.Executors;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.Items;
import com.cubemc.api.Utils.M;
import com.cubemc.api.game.executors.StartGameExecutor;
import com.cubemc.targeted.Abilities.EMP;
import com.cubemc.targeted.Abilities.SmokeBomb;
import com.cubemc.targeted.Abilities.TimedSprint;
import com.cubemc.targeted.Abilities.Tracker;
import com.cubemc.targeted.Targeted;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class StartGame implements StartGameExecutor {

    @Override
    public void startWarmup() {
        Location current = Targeted.mapSpawns.get(CubeAPI.getGameManager().getGame().getCurrentMap().getFolderName()).get(0);

        for (Player p : Bukkit.getOnlinePlayers()){
            CubeAPI.getGameManager().getTeamManager().joinTeam(p, CubeAPI.getGameManager().getTeamManager().getTeam("Players"));

            Location next = getNextSpawn(current);
            if (next == null){
                p.sendMessage(M.fatal("The location you are trying to teleport to is null!"));
                p.sendMessage(M.fatal("Spawns size: §7" + Targeted.mapSpawns.get(CubeAPI.getGameManager().getGame().getCurrentMap().getFolderName()).size()));
            }else{
                p.teleport(next.clone());
                p.getInventory().clear();
                p.setFoodLevel(6);
            }
            current = next;
        }
    }

    @Override
    public void startGame() {
        for (Player p : Bukkit.getOnlinePlayers()){
            p.getInventory().addItem(Items.create(Material.WOOD_SWORD, 1, "§c§lWEAPON", Arrays.asList("§eUse this to kill your target.")));
            Tracker.giveItem(p);
            TimedSprint.giveItem(p);
            SmokeBomb.giveItem(p);
            EMP.giveItem(p);
            Targeted.getTargetManager().givePlayerTarget(p);
        }

        CubeAPI.getGameManager().getGame().getSet().setCanPVE(true);
        CubeAPI.getGameManager().getGame().getSet().setCanPVP(true);
        CubeAPI.getGameManager().getGame().getSet().setCanPlayersTakeDamage(true);
    }

    public Location getNextSpawn(Location prev){
        List<Location> spawns = Targeted.mapSpawns.get(CubeAPI.getGameManager().getGame().getCurrentMap().getFolderName());
        int lastN = spawns.indexOf(prev);
        int next = lastN + 1;

        if (next == spawns.size()) next = 0;

        return spawns.get(next);
    }
}
