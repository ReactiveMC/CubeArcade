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
public class EMP {

    public static void giveItem(Player p){
        p.getInventory().setItem(8, Items.create(Material.COMMAND, 1, "§b§lEMP §7(Right-Click)", Arrays.asList("§eUse this to disable your attacker's compass.")));
    }

}
