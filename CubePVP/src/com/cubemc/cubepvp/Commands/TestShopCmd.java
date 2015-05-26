package com.cubemc.cubepvp.Commands;

import com.cubemc.api.Commands.CommandBase;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Shop.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 25/05/2015
 */
public class TestShopCmd extends CommandBase<GameCommandManager> {

    public TestShopCmd(GameCommandManager plugin) {
        super(plugin, Rank.MEMBER, Arrays.asList("testshop", "shoptest", "ts", "st"));
    }

    @Override
    public void execute(Player p, String[] args) {
        ActionHandler shopHandler = new ActionHandler() {
            @Override
            public void onGoBackAction(Player player, ShopMenu shopMenu) {
                player.closeInventory();
                return;
            }

            @Override
            public void onActivate(Player player, ShopItem shopItem) {
                return;
            }

            @Override
            public boolean checkIfActive(Player player, ShopItem shopItem) {
                return false;
            }
        };

        ShopMenu menu = new ShopMenu("CubePVP Kit Shop", Material.NAME_TAG, shopHandler);

        ActionHandler ih = new ActionHandler() {
            @Override
            public void onGoBackAction(Player player, ShopMenu shopMenu) {
                return;
            }

            @Override
            public void onActivate(Player player, ShopItem shopItem) {
                return;
            }

            @Override
            public boolean checkIfActive(Player player, ShopItem shopItem) {
                if (ShopUtil.hasBoughtItem(shopItem, player)==true){
                    return true;
                }
                return false;
            }
        };

        ShopItem a = new ShopItem("Archer Kit", Arrays.asList("Here is the long range", "kit just to show you", "how they can be bought."), "CubePVP Kits", 100, CurrencyType.GEMS, false, Rank.MEMBER, Material.BOW);
        a.setActionIfAlreadyHad(ih);
        menu.addItem(a);

        menu.openShop(p);
    }
}
