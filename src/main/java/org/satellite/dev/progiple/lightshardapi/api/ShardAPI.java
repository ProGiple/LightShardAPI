package org.satellite.dev.progiple.lightshardapi.api;

import lombok.experimental.UtilityClass;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.novasparkle.lunaspring.API.menus.items.NonMenuItem;
import org.novasparkle.lunaspring.API.util.service.managers.NBTManager;
import org.satellite.dev.progiple.lightshardapi.Config;
import org.satellite.dev.progiple.lightshardapi.Shard;

import java.util.Arrays;
import java.util.stream.Stream;

@UtilityClass
public class ShardAPI {
    public Shard createItem(int amount) {
        return new Shard(Config.getSection("item"), amount);
    }

    public Shard createItem() {
        return createItem(1);
    }

    public ItemStack createStack(int amount) {
        return createItem(amount).getDefaultStack();
    }

    public ItemStack createStack() {
        return createStack(1);
    }

    public boolean isShard(NonMenuItem item) {
        return item instanceof Shard;
    }

    public boolean isShard(ItemStack itemStack) {
        return itemStack != null && !itemStack.getType().isAir() && NBTManager.hasTag(itemStack, "lightshardAPI");
    }

    public int takeShards(Inventory inventory, int amount) {
        for (ItemStack content : inventory.getStorageContents()) {
            if (amount <= 0) break;

            if (ShardAPI.isShard(content)) {
                int different = Math.min(amount, content.getAmount());
                amount -= different;
                content.setAmount(content.getAmount() - different);
            }
        }

        return amount;
    }

    public Stream<ItemStack> getShards(Inventory inventory) {
        return Arrays.stream(inventory.getStorageContents()).filter(ShardAPI::isShard);
    }
}
