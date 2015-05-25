package com.cubemc.cubepvp.Kits;

import com.cubemc.api.Game.kits.GameKit;
import com.cubemc.api.Game.kits.perks.Perk;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Shop.CurrencyType;
import com.cubemc.api.Shop.ShopItem;
import com.cubemc.api.Utils.Items;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class ArcherKit extends GameKit {

    public ArcherKit() {
        super("Archer", Arrays.asList("§9If you like long range",
                        "§9then this kit is for you!",
                        "",
                        "§9Contains:",
                        "§7Stone Sword",
                        "§7Infinity I Bow",
                        "§7Iron Armour"), Material.BOW,
                new ShopItem("Archer Kit", Arrays.asList(""), "CubePVP Kits", 100, CurrencyType.GEMS, false, Rank.MEMBER, Material.BOW), new Perk[]{});
    }

    @Override
    public void applyKit(Player player) {
        player.getInventory().setArmorContents(new ItemStack[]{
                Items.create(Material.IRON_BOOTS, 1, "§7Knight - §fIron Boots", Arrays.asList("")),
                Items.create(Material.IRON_LEGGINGS, 1, "§7Knight - §fIron Leggings", Arrays.asList("")),
                Items.create(Material.IRON_CHESTPLATE, 1, "§7Knight - §fIron Chestplate", Arrays.asList("")),
                Items.create(Material.IRON_HELMET, 1, "§7Knight - §fIron Helmet", Arrays.asList(""))
        });
        player.getInventory().addItem(Items.create(Material.STONE_SWORD, 1, "§7Archer - §fStone Sword", Arrays.asList("")));
        ItemStack bow = Items.create(Material.BOW, 1, "§7Archer - §fBow", Arrays.asList(""));
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(Items.create(Material.ARROW, 1, "§7Archer - §fArrow", Arrays.asList("")));
    }

}
