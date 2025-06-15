package org.satellite.dev.progiple.lightshardapi;

import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.novasparkle.lunaspring.API.configuration.IConfig;

@UtilityClass
public class Config {
    private final IConfig config;
    static {
        config = new IConfig(LightShardAPI.getINSTANCE());
    }

    public void reload() {
        config.reload(LightShardAPI.getINSTANCE());
    }

    public ConfigurationSection getSection(String path) {
        return config.getSection(path);
    }

    public void sendMessage(CommandSender sender, String id, String... rpl) {
        config.sendMessage(sender, id, rpl);
    }
}
