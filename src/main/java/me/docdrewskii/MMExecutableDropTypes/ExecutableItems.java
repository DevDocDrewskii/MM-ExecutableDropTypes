package me.docdrewskii.MMExecutableDropTypes;

import com.ssomar.score.api.executableitems.ExecutableItemsAPI;
import com.ssomar.score.api.executableitems.config.ExecutableItemInterface;
import io.lumine.mythic.api.adapters.AbstractItemStack;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IItemDrop;
import io.lumine.mythic.bukkit.BukkitAdapter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;


public class ExecutableItems implements IItemDrop {
    private final String executableItemId;

    public ExecutableItems(MythicLineConfig config, String argument) {
        // Read “identification” or “id,” fallback to argument
        this.executableItemId = config.getString(new String[]{ "identification", "id" }, argument);
    }

    @Override
    public AbstractItemStack getDrop(DropMetadata data, double amount) {
        // Try to get the player from DropMetadata
        Player player = data.getCaster().getEntity().getBukkitEntity() instanceof Player
                ? (Player) data.getCaster().getEntity().getBukkitEntity()
                : null;

        // Fetch the ExecutableItem
        Optional<ExecutableItemInterface> eiOpt = ExecutableItemsAPI.getExecutableItemsManager().getExecutableItem(executableItemId);
        if (eiOpt.isPresent()) {
            ItemStack item = eiOpt.get().buildItem((int) amount, Optional.empty(), Optional.ofNullable(player));
            if (item != null) {
                return BukkitAdapter.adapt(item);
            }
        }

        // Return null or fallback item if not found
        return null;
    }
}
