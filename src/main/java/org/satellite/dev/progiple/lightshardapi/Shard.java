package org.satellite.dev.progiple.lightshardapi;

import lombok.NonNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.novasparkle.lunaspring.API.menus.items.NonMenuItem;
import org.novasparkle.lunaspring.API.util.service.managers.NBTManager;

public class Shard extends NonMenuItem {
    public Shard(@NonNull ConfigurationSection section, int amount) {
        super(section);
        this.setAmount(Math.max(amount, 1));
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = super.getDefaultStack();
        NBTManager.setString(itemStack, "lightshardAPI", "shard");
        return itemStack;
    }
}
