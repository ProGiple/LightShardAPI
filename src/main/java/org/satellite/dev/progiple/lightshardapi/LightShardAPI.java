package org.satellite.dev.progiple.lightshardapi;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.novasparkle.lunaspring.API.commands.LunaExecutor;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.dev.progiple.lightshardapi.api.ShardAPI;

import java.util.Arrays;
import java.util.Objects;

public final class LightShardAPI extends LunaPlugin {
    @Getter private static LightShardAPI INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        saveDefaultConfig();
        super.onEnable();

        LunaExecutor.initialize(this);
        this.createPlaceholder("shards", (player, params) -> {
            if (!player.isOnline()) return "Оффлайн!";

            Inventory inventory = Objects.requireNonNull(player.getPlayer()).getInventory();
            if (params.equalsIgnoreCase("has")) {
                return Arrays.stream(inventory.getStorageContents()).anyMatch(ShardAPI::isShard) ? "yes" : "no";
            }

            if (params.equalsIgnoreCase("amount")) {
                return String.valueOf(Arrays.stream(inventory.getStorageContents())
                        .filter(ShardAPI::isShard)
                        .mapToInt(ItemStack::getAmount)
                        .sum());
            }
            return null;
        });
    }
}
