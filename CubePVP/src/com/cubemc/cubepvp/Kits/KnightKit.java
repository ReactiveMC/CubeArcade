package com.cubemc.cubepvp.Kits;

import com.cubemc.api.Game.kits.GameKit;
import com.cubemc.api.Game.kits.perks.Perk;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Shop.CurrencyType;
import com.cubemc.api.Shop.ShopItem;
import com.cubemc.api.Utils.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class KnightKit extends GameKit {

    public KnightKit() {
        super("Knight", Arrays.asList("§9For the warriors of the world",
                "§9who normally prefer the sword!",
                "",
                "§9Contains:",
                "§7Diamond Sword",
                "§7Iron Armour"), Material.IRON_CHESTPLATE,
                new ShopItem("Knight Kit", Arrays.asList(""), "CubePVP Kits", 0, CurrencyType.GEMS, false, Rank.MEMBER, Material.IRON_CHESTPLATE), new Perk[]{});
    }

    @Override
    public void applyKit(Player player) {
        player.getInventory().setArmorContents(new ItemStack[]{
                Items.create(Material.IRON_HELMET, 1, "§7Knight - §fIron Helmet", Arrays.asList("")),
                Items.create(Material.IRON_CHESTPLATE, 1, "§7Knight - §fIron Chestplate", Arrays.asList("")),
                Items.create(Material.IRON_LEGGINGS, 1, "§7Knight - §fIron Leggings", Arrays.asList("")),
                Items.create(Material.IRON_BOOTS, 1, "§7Knight - §fIron Boots", Arrays.asList(""))
        });
        player.getInventory().addItem(Items.create(Material.DIAMOND_SWORD, 1, "§7Knight - §fDiamond Sword", Arrays.asList("")));
    }
}
