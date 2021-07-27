package me.notdew.wheatmoney.events;

import me.notdew.wheatmoney.WheatMoney;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import net.milkbowl.vault.economy.EconomyResponse;

public class WheatBreakEvent implements Listener {

    private static Economy econ = null;


    @EventHandler
    public void onWheatBreak(BlockBreakEvent e) {
        Player sender = e.getPlayer();
        Player player = e.getPlayer();
        Economy eco = WheatMoney.getEconomy();
        BlockData blockdata = e.getBlock().getBlockData();
        if (blockdata instanceof Ageable) {
            if (e.getPlayer().getLocation().getWorld().getName().equals("world")) {
                Ageable age = (Ageable) blockdata;

                if (age.getAge() == age.getMaximumAge()) {
                    ((Ageable) blockdata).setAge(0);
                    EconomyResponse r = eco.depositPlayer(player, 100.00);
                    player.sendMessage(ChatColor.BLUE + "[" + ChatColor.WHITE + "+$100" + ChatColor.BLUE + "]");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 8, 0);
                }
                if (age.getAge() != age.getMaximumAge()) {
                    e.setCancelled(true);
                    ((Ageable) blockdata).setAge(0);
                }


                e.setCancelled(true);
                Block block = e.getBlock();
                block.setType(Material.WHEAT, true);
                block.setBlockData(blockdata);



            }
        }
    }
}
