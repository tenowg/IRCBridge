package com.thedemgel.ircbridge.commands;

import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.exception.CommandException;
import com.thedemgel.ircbridge.IRCBridge;

public class PlayerCommands {
	private final IRCBridge plugin;

	public PlayerCommands(IRCBridge instance) {
		this.plugin = instance;
	}

	@Command(aliases = {"command", "cmd"}, usage = "No Usage, replace this command", desc = "This is just an Example. Replace it.", min = 0, max = 0)
	@CommandPermissions("IRCBridge.some.permission")
	public void aCommand(CommandContext args, CommandSource source) throws CommandException {
		// Do Some Command
	}
}
