
package com.thedemgel.ircbridge;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.spout.api.Spout;


public class ServerPirc {
	private PircBotX bot;
	private ConcurrentMap<String, Channel> channels = new ConcurrentHashMap<String, Channel>();
	
	public void init() {
		bot = new PircBotX();
		
		bot.setName("Server");
		bot.setLogin("Server");
		
		try {
			bot.connect("localhost", 6667, "demgelserver");
		} catch (IOException ex) {
			Spout.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
		} catch (IrcException ex) {
			Spout.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
		}
		
		channels.put("server", bot.getChannel("#Server"));
		channels.put("notices", bot.getChannel("#notices"));
		channels.put("joinleave", bot.getChannel("#joinleave"));
		
		for (Channel channel : channels.values()) {
			bot.joinChannel(channel.getName());
		}
	}
	
	public void sendJoinMessage(String message) {
		channels.get("joinleave").sendMessage(message);
	}
}
