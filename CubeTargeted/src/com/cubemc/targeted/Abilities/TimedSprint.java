package com.cubemc.targeted.Abilities;

import com.cubemc.api.Utils.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class TimedSprint {

    public static void giveItem(Player p){
        p.getInventory().setItem(6, Items.create(Material.SUGAR, 1, "§a§lTIMED SPRINT §7(Right-Click)", Arrays.asList("§eUse this to gain sprint for a time period.")));
    }

}
