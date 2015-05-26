package com.cubemc.targeted.Abilities;

import com.cubemc.api.Utils.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class Tracker {

    public static void giveItem(Player p){
        p.getInventory().addItem(Items.create(Material.COMPASS, 1, "§6§lTRACKER", Arrays.asList("§eUse this to track your target down.", "§7§oCan be attacked by EMP.")));
        return;
    }

}
