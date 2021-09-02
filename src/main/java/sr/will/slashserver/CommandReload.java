package sr.will.slashserver;

import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class CommandReload implements SimpleCommand {
    private static final TextComponent RELOAD_MESSAGE = Component.text("Plugin reloaded!", NamedTextColor.GREEN);

    private SlashServer plugin;

    public CommandReload(SlashServer plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Invocation invocation) {
        plugin.reload();
        invocation.source().sendMessage(RELOAD_MESSAGE);
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("slashserver.reload");
    }
}
