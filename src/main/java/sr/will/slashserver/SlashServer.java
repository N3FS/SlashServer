package sr.will.slashserver;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Plugin(
        id = "slashserver",
        name = "SlashServer",
        version = "${fullVersion}",
        description = "A plugin to add slash aliases for servers.",
        authors = {"Willsr71", "mdcfe"},
        url = "https://github.com/N3FS/SlashServer"
)
public class SlashServer {
    @Inject
    private ProxyServer proxy;
    private List<String> registeredCommands = new ArrayList<>();

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        proxy.getCommandManager().register("ssreload", new CommandReload(this));

        reload();
    }

    public void reload() {
        registeredCommands.forEach(name -> proxy.getCommandManager().unregister(name));
        registeredCommands.clear();

        for (RegisteredServer server : proxy.getAllServers()) {
            String name = server.getServerInfo().getName().toLowerCase(Locale.ROOT);
            proxy.getCommandManager().register(name, new CommandServer(server));
            registeredCommands.add(name);
        }
    }
}
