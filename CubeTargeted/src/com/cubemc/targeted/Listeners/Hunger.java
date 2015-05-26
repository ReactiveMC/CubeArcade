package com.cubemc.targeted.Listeners;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.game.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class Hunger implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();

        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME){
            e.setFoodLevel(20);
            return;
        }

        if (e.getFoodLevel() < 6){
            e.setFoodLevel(6);
        }
    }

}
