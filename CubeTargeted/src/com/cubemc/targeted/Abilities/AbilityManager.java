package com.cubemc.targeted.Abilities;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
import com.cubemc.api.game.GameState;
import com.cubemc.targeted.Targeted;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class AbilityManager extends Module {

    public AbilityManager(JavaPlugin plugin) {
        super("Ability Manager", plugin);
    }

    @EventHandler
    public void TimedSprint(PlayerInteractEvent e){
        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() == null) return;
        if (!(e.getItem().getType().equals(Material.SUGAR))) return;

        e.setCancelled(true);

        final Player p = e.getPlayer();

        if (!(CubeAPI.getRechargeManager().useAbility("Timed Sprint", 30000L, p, true, true))){
            return;
        }

        p.setFoodLevel(20);

        Bukkit.getScheduler().scheduleSyncDelayedTask(CubeAPI.getPlugin(), new Runnable() {
            @Override
            public void run() {
                p.setFoodLevel(6);
            }
        }, 100L);
    }

    @EventHandler
    public void SmokeBomb(PlayerInteractEvent e){
        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() == null) return;
        if (!(e.getItem().getType().equals(Material.SULPHUR))) return;

        e.setCancelled(true);

        Player p = e.getPlayer();

        if (!(CubeAPI.getRechargeManager().useAbility("Smoke Bomb", 45000L, p, true, true))){
            return;
        }

        p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 5);
        new PotionEffect(PotionEffectType.INVISIBILITY, 100, 2).apply(p);
    }

    @EventHandler
    public void EMP(PlayerInteractEvent e){
        if (CubeAPI.getGameManager().getGame().getState() != GameState.INGAME) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() == null) return;
        if (!(e.getItem().getType().equals(Material.COMMAND))) return;

        e.setCancelled(true);

        final Player p = e.getPlayer();

        if (Targeted.getTargetManager().getAttacker(p)==null){
            p.sendMessage(M.error("You don't have anyone targeting you."));
            return;
        }

        Player a = Targeted.getTargetManager().getAttacker(p);

        if (a.getLocation().distance(p.getLocation())>15){
            p.sendMessage(M.error("Your attacker must be within 15 blocks."));
            return;
        }

        if (!(CubeAPI.getRechargeManager().useAbility("EMP", 20000L, p, true, true))){
            return;
        }

        a.setCompassTarget(new Location(a.getWorld(), 0, 0, 0));
        a.sendMessage(M.error("You've been hit by an EMP attack!"));

        Bukkit.getScheduler().scheduleSyncDelayedTask(CubeAPI.getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (Targeted.getTargetManager().getAttacker(p) != null){
                    Targeted.getTargetManager().getAttacker(p).setCompassTarget(p.getLocation());
                }
            }
        }, 100L);
    }

}
