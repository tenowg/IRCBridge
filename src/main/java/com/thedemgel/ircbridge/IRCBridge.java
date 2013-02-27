package com.thedemgel.ircbridge;

import com.thedemgel.ircbridge.commands.PlayerCommands;
import com.thedemgel.ircbridge.configuration.IRCBridgeConfiguration;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.RootCommand;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.plugin.CommonPlugin;

/**
 * If you have found this useful, please let me know.
 * @author Craig <tenowg at thedemgel.com>
 */
public class IRCBridge extends CommonPlugin {
	private static IRCBridge instance;
	private IRCBridgeConfiguration config;
	private ServerPirc irc;

	@Override
	public void onLoad() {
		setInstance(this);
		config = new IRCBridgeConfiguration(getDataFolder());
		config.load();
		getLogger().info("loaded.");
	}

	@Override
	public void onEnable() {
		//Commands
		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		RootCommand root = getEngine().getRootCommand();
		root.addSubCommands(this, PlayerCommands.class, commandRegFactory);

		getEngine().getEventManager().registerEvents(new EListener(this), this);
		
		irc = new ServerPirc();
		irc.init();
		
		getLogger().info("enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info("disabled.");
	}

	private static void setInstance(IRCBridge plugin) {
		instance = plugin;
	}

	public static IRCBridge getInstance() {
		return instance;
	}

	public IRCBridgeConfiguration getConfig() {
		return config;
	}
	
	public ServerPirc getIrc() {
		return irc;
	}
}
