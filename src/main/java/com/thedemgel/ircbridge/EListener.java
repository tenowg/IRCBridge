
package com.thedemgel.ircbridge;

import com.thedemgel.ircbridge.components.IRCConnection;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.Listener;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.event.player.PlayerLeaveEvent;

/**
 * A Basic Event Listener for Spout
 */
public class EListener implements Listener {
	private IRCBridge plugin;

	public EListener(IRCBridge instance) {
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		p.add(IRCConnection.class);
		
		IRCBridge.getInstance().getIrc().sendJoinMessage(event.getMessage().asString());
		
		event.setMessage("");
	}
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		IRCConnection con = event.getPlayer().get(IRCConnection.class);
		
		con.sendMessage(event.getMessage().asString());
		event.getPlayer().sendMessage(ChatStyle.YELLOW, "You", ChatStyle.RESET, ": ", ChatStyle.GRAY, event.getMessage());
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerLeaveEvent event) {
		IRCConnection con = event.getPlayer().get(IRCConnection.class);
		con.disconnect();
	}
}
