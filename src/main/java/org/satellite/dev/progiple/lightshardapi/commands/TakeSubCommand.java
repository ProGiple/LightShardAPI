package org.satellite.dev.progiple.lightshardapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.novasparkle.lunaspring.API.commands.LunaCompleter;
import org.novasparkle.lunaspring.API.commands.annotations.Check;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.novasparkle.lunaspring.API.util.utilities.LunaMath;
import org.novasparkle.lunaspring.API.util.utilities.Utils;
import org.satellite.dev.progiple.lightshardapi.Config;
import org.satellite.dev.progiple.lightshardapi.api.ShardAPI;

import java.util.List;

@SubCommand(appliedCommand = "lightshardapi", commandIdentifiers = {"take"})
@Check(permissions = {"lightshardapi.take"}, flags = {})
public class TakeSubCommand implements LunaCompleter {
    @Override
    public void invoke(CommandSender sender, String[] strings) {
        if (strings.length < 2) {
            Config.sendMessage(sender, "noArgs");
            return;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayerIfCached(strings[1]);
        if (offlinePlayer == null || !offlinePlayer.isOnline()) {
            Config.sendMessage(sender, "playerIsOffline", "player-%-" + strings[1]);
            return;
        }

        int amount = strings.length >= 3 ? LunaMath.toInt(strings[2]) : 1;
        final int finalAmount = amount;

        Inventory inventory = ((Player) offlinePlayer).getInventory();
        amount = ShardAPI.takeShards(inventory, amount);

        Config.sendMessage(sender, "take", "player-%-" + offlinePlayer.getName(), "amount-%-" + (finalAmount - amount));
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, List<String> list) {
        return list.isEmpty() ? null :
                (list.size() == 1 ? Utils.getPlayerNicks(list.get(0)) :
                        (list.size() == 2 ? List.of("<количество>") : null));
    }
}
