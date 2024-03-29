package com.cubemc.cubepvp;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.game.CubeGame;
import com.cubemc.api.game.GamePlugin;
import com.cubemc.api.game.GameState;
import com.cubemc.api.game.kits.GameKit;
import com.cubemc.api.game.lobby.JoinAction;
import com.cubemc.api.game.maps.GameMap;
import com.cubemc.api.game.prevention.PreventionSet;
import com.cubemc.api.game.teams.GameTeam;
import com.cubemc.cubepvp.Commands.GameCommandManager;
import com.cubemc.cubepvp.Executors.EndGame;
import com.cubemc.cubepvp.Executors.StartGame;
import com.cubemc.cubepvp.Kits.ArcherKit;
import com.cubemc.cubepvp.Kits.KnightKit;
import com.cubemc.cubepvp.Listeners.Death;
import com.cubemc.cubepvp.Listeners.Join;
import com.cubemc.cubepvp.Listeners.Leave;
import com.cubemc.cubepvp.Listeners.NoHunger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class CubePVP extends JavaPlugin implements GamePlugin {

    @Override
    public void onEnable() {
        //List all maps for the game.
        List<GameMap> maps = new ArrayList<GameMap>();

        maps.add(new GameMap("Dark Forest", "WilliamTiger", "dark_forest"));

        //Initialize the game with the GameManager.
        CubeAPI.getGameManager().initializeGame(setupGame(), maps);

        //Test shop command (JUST FOR TESTING LOL)
        new GameCommandManager(this);

        //Listeners
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Death(), this);
        Bukkit.getPluginManager().registerEvents(new Leave(), this);
        Bukkit.getPluginManager().registerEvents(new NoHunger(), this);
    }

    @Override
    public CubeGame setupGame() {
        CubeGame game = new CubeGame("CubePVP", "PVP", "§6", new PreventionSet());

        game.setState(GameState.WAITING);
        game.setTicks(45);
        game.setDescription(Arrays.asList("Choose a kit.", "Fight the other team.", "Last team alive wins."));
        game.setStartGameExecutor(new StartGame());
        game.setEndGameExecutor(new EndGame());
        game.setSubtitles("Classic Team vs Team");
        game.setEnforcePlayableArena(false);
        game.setJoinAction(JoinAction.SEND_TO_LOBBY);
        game.setKitsEnabled(true);
        game.setMaxPlayers(10);
        game.setMinPlayers(2);

        List<GameTeam> teams = new ArrayList<GameTeam>();
        {
            GameTeam team = new GameTeam("Red", "§c");
            team.setMaxSize(5);
            teams.add(team);
        }
        {
            GameTeam team = new GameTeam("Blue", "§9");
            team.setMaxSize(5);
            teams.add(team);
        }
        {
            GameTeam team = new GameTeam("Spectators", "§7");
            team.setMaxSize(-1);
            team.setVisible(false);
            teams.add(team);
        }
        game.setTeams(teams);

        List<GameKit> kits = new ArrayList<GameKit>();
        kits.add(new KnightKit());
        kits.add(new ArcherKit());
        game.setGameKits(kits);

        return game;
    }
}
