package org.satellite.dev.progiple.lightshardapi.commands;

import org.bukkit.command.CommandSender;
import org.novasparkle.lunaspring.API.commands.Invocation;
import org.novasparkle.lunaspring.API.commands.annotations.Check;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.satellite.dev.progiple.lightshardapi.Config;

@SubCommand(appliedCommand = "lightshardapi", commandIdentifiers = {"reload"})
@Check(permissions = {"lightshardapi.reload"}, flags = {})
public class ReloadSubCommand implements Invocation {
    @Override
    public void invoke(CommandSender commandSender, String[] strings) {
        Config.reload();
        Config.sendMessage(commandSender, "reload");
    }
}
