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
public class SmokeBomb {

    public static void giveItem(Player p){
        p.getInventory().setItem(7, Items.create(Material.SULPHUR, 1, "§9§lSMOKE BOMB §7(Right-Click)", Arrays.asList("§eUse this to become invisible for a time period.")));
    }

}
