package me.docdrewskii.MMExecutableDropTypes;

import io.lumine.mythic.bukkit.events.MythicDropLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class MMExecutableDropTypes extends JavaPlugin implements Listener {

    private Logger log;

    @Override
    public void onEnable() {
        log = this.getLogger();
        Bukkit.getPluginManager().registerEvents(this, this);

        log.info("MythicMobs API Examples Plugin Enabled!");
    }

    public void onDisable(){
        log.info("MythicMobs API Examples Plugin Disabled!");
    }

    @EventHandler
    public void onMythicDropLoad(MythicDropLoadEvent event)	{
        log.info("MythicDropLoadEvent called for drop " + event.getDropName());

        if(event.getDropName().equalsIgnoreCase("EI"))	{
            event.register(new ExecutableItems(event.getConfig(), event.getArgument()));
            log.info("-- Registered EI Drop!");
        }
    }

}
