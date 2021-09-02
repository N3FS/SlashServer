package sr.will.slashserver;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Optional;

public class CommandServer implements SimpleCommand {
    private static final TextComponent PLAYERS_ONLY = Component.text("Only players can teleport between servers.", NamedTextColor.RED);
    private static final TextComponent SAME_SERVER = Component.text("You're already connected to this server!", NamedTextColor.RED);
    private static final TextComponent CONNECT_FAILED = Component.text("Failed to connect! Please notify an admin.", NamedTextColor.RED);

    private final RegisteredServer server;
    private final TextComponent successMessage;

    public CommandServer(RegisteredServer server) {
        this.server = server;
        this.successMessage = Component.text("You have been sent to " + this.server.getServerInfo().getName() + ".", NamedTextColor.GREEN);
    }

    private String serverName() {
        return this.server.getServerInfo().getName().toLowerCase();
    }

    @Override
    public void execute(Invocation invocation) {
        final CommandSource source = invocation.source();

        if (!(source instanceof Player)) {
            source.sendMessage(PLAYERS_ONLY);
            return;
        }

        Player player = (Player) source;
        Optional<ServerConnection> connection = player.getCurrentServer();
        if (connection.isPresent() && connection.get().getServer() == server) {
            source.sendMessage(SAME_SERVER);
            return;
        }

        player.createConnectionRequest(server).connect().thenAccept(result -> {
            if (result.isSuccessful()) {
                source.sendMessage(successMessage);
            } else {
                source.sendMessage(CONNECT_FAILED);
            }
        });
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("slashserver." + serverName());
    }
}
