
package net.welcomeui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class WelcomeUI extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("[WelcomeUI] Plugin enabled.");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int delayTicks = getConfig().getInt("welcome.delay", 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                openWelcomeUI(player);
            }
        }.runTaskLater(this, delayTicks);
    }

    private void openWelcomeUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "§b§l歡迎你來到星際城堡");

        // 按鈕僅作裝飾，玩家按 ESC 關閉即可
        ItemStack infoItem = new ItemStack(Material.BOOK);
        ItemMeta infoMeta = infoItem.getItemMeta();
        infoMeta.setDisplayName("§e請閱讀此畫面, 按 ESC 關閉");
        infoItem.setItemMeta(infoMeta);
        gui.setItem(13, infoItem);

        player.openInventory(gui);
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
    }
}
