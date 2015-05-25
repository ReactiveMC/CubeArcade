package com.cubemc.cubepvp.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 25/05/2015
 */
public class NoHunger implements Listener {

    @EventHandler
    public void onChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

}
