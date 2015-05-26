package com.cubemc.targeted;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.game.CubeGame;
import com.cubemc.api.game.GamePlugin;
import com.cubemc.api.game.GameState;
import com.cubemc.api.game.lobby.JoinAction;
import com.cubemc.api.game.maps.GameMap;
import com.cubemc.api.game.prevention.PreventionSet;
import com.cubemc.api.game.teams.GameTeam;
import com.cubemc.targeted.Abilities.AbilityManager;
import com.cubemc.targeted.Executors.EndGame;
import com.cubemc.targeted.Executors.StartGame;
import com.cubemc.targeted.Listeners.Hunger;
import com.cubemc.targeted.Listeners.Join;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class Targeted extends JavaPlugin implements GamePlugin {

    /*
    Inventory and Abilities:

    - Tracker (COMPASS): Use this to track down your target.
    - Weapon (WOOD SWORD): Use this to attack your target.

    - Timed Sprint (SUGAR): Use this to restore your hunger so that you can sprint to get away.
    - Smoke Bomb (GUNPOWDER): Use this to disappear in a puff of smoke. (Smoke effects, invisible for 5 seconds).
    - EMP (COMMAND BLOCK): Use this to throw off your attacker's compass for 10 seconds if they are within 15 blocks of you.

     */

    public static HashMap<String, List<Location>> mapSpawns = new HashMap<String, List<Location>>();

    private static AbilityManager abilityManager;
    private static TargetManager targetManager;

    @Override
    public void onEnable() {
        List<GameMap> maps = new ArrayList<GameMap>();
        maps.add(new GameMap("Test Map", "WilliamTiger", "test_map"));

        CubeAPI.getGameManager().initializeGame(setupGame(), maps);

        //Bukkit.getPluginManager().registerEvents(new EndChecker(), this);
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Hunger(), this);

        setupMapSpawns();

        abilityManager = new AbilityManager(this);
        targetManager = new TargetManager(this);
    }

    @Override
    public CubeGame setupGame() {
        CubeGame game = new CubeGame("Targeted", "TGD", "§c", new PreventionSet());

        game.setState(GameState.WAITING);
        game.setTicks(20);
        game.setDescription(Arrays.asList("Kill your assigned target.", "Receive their previous target.", "Run from your assassin."));
        game.setStartGameExecutor(new StartGame());
        game.setEndGameExecutor(new EndGame());
        game.setSubtitles("Assassination Mode");
        game.setEnforcePlayableArena(false);
        game.setJoinAction(JoinAction.SEND_TO_LOBBY);
        game.setKitsEnabled(false);
        game.setMaxPlayers(10);
        game.setMinPlayers(1);

        List<GameTeam> teams = new ArrayList<GameTeam>();
        {
            GameTeam team = new GameTeam("Players", "§e");
            team.setMaxSize(10);
            teams.add(team);
        }
        {
            GameTeam team = new GameTeam("Spectators", "§7");
            team.setVisible(false);
            teams.add(team);
        }
        game.setTeams(teams);

        return game;
    }

    public void setupMapSpawns(){
        saveDefaultConfig();

        for (String map : getConfig().getConfigurationSection("spawns").getKeys(false)){
            List<Location> spawns = new ArrayList<Location>();
            for (String ltp : getConfig().getStringList("spawns." + map)){
                String[] splits = ltp.split(" ");
                int x = Integer.parseInt(splits[0]);
                int y = Integer.parseInt(splits[1]);
                int z = Integer.parseInt(splits[2]);
                World w = Bukkit.getWorld(map);
                Location loc = new Location(w, x, y, z);
                spawns.add(loc);
            }
            mapSpawns.put(map, spawns);
        }
    }

    public static AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public static TargetManager getTargetManager() {
        return targetManager;
    }
}
