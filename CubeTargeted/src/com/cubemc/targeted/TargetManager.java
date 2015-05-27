package com.cubemc.targeted;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.M;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 26/05/2015
 */
public class TargetManager extends Module {

    private HashMap<String, String> targets = new HashMap<String, String>();

    public TargetManager(JavaPlugin plugin) {
        super("Target Manager", plugin);
    }

    public boolean hasTarget(Player p){
        if (targets.containsKey(p.getName())) return true;
        return false;
    }

    public Player getAttacker(Player p){
        if (!(targets.containsValue(p.getName()))) return null;
        for (String t : targets.keySet()){
            if (targets.get(t).equalsIgnoreCase(p.getName())){
                return Bukkit.getPlayer(t);
            }
        }
        return null;
    }

    public void stealTargetFromPlayer(Player stealer, Player victim){
        if (hasTarget(victim)){
            String nt = targets.get(victim.getName());
            targets.remove(victim.getName());
            targets.put(stealer.getName(), nt);
            stealer.sendMessage(M.reg("You have acquired a new target - §c§l" + nt + "§7."));
            stealer.setCompassTarget(Bukkit.getPlayer(nt).getLocation());
        }else{
            stealer.sendMessage(M.error("There is §lno target §cto steal."));
            return;
        }
    }

    public void givePlayerTarget(Player p){
        if (targets.containsKey(p.getName())){
            //Player already has a target.
            if (Bukkit.getPlayer(targets.get(p.getName())).isOnline()) {
                return; //They don't need a new target.
            }
        }

        Collection<String> alreadyTargets = targets.values();
        List<String> possible = CubeAPI.getGameManager().getTeamManager().getTeam("Players").getMembers();
        Random r = new Random();
        String newtarget = null;
        while (newtarget == null){

            if (possible.isEmpty()){
                newtarget = "Empty";
                continue;
            }

            int i = r.nextInt((possible.size() - 1) - 0 + 1) + 0;
            String attempt = possible.get(i);

            if ((alreadyTargets.contains(attempt)) || (p.getName().equalsIgnoreCase(attempt))){
                possible.remove(attempt);
                continue; //Failure: Try again.
            }

            if (targets.containsKey(attempt)){
                if (targets.get(attempt).equalsIgnoreCase(p.getName())){
                    if (!(CubeAPI.getGameManager().getTeamManager().getTeam("Players").getMembers().size() == 2)){
                        //Failure: They would both have the same target in one go before the final 2 competitors.
                        continue;
                    }
                }
            }

            newtarget = attempt; //Success: New target found, while loop ends.
        }

        if (newtarget.equalsIgnoreCase("Empty")){
            p.sendMessage(M.error("There are §lno possible §ctargets."));
            return;
        }

        p.sendMessage(M.reg("You have acquired a new target - §c§l" + newtarget + "§7."));
        targets.put(p.getName(), newtarget);
        p.setCompassTarget(Bukkit.getPlayer(newtarget).getLocation());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (!((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player))) return;

        Player p = (Player) e.getEntity();
        Player d = (Player) e.getDamager();

        if (targets.containsKey(d.getName())){
            if (!(targets.get(d.getName()).equalsIgnoreCase(p.getName()))){
                e.setCancelled(true);
                d.sendMessage(M.reg("§c" + p.getName() + " §7is not your target."));
                return;
            }
        }
    }
}
