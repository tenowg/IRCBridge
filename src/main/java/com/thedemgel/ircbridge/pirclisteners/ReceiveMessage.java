
package com.thedemgel.ircbridge.pirclisteners;

import com.thedemgel.ircbridge.components.IRCConnection;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.spout.api.chat.style.ChatStyle;
import org.spout.api.entity.Player;


public class ReceiveMessage extends ListenerAdapter {
	private IRCConnection bot;
	
	public ReceiveMessage(IRCConnection con) {
		bot = con;
	}
	
	@Override
	public void onMessage(MessageEvent event) {
		Player p = (Player)bot.getOwner();
		p.sendMessage(ChatStyle.CYAN, event.getUser().getNick(), "@", event.getUser().getHostmask(), ChatStyle.RESET, ": ", event.getMessage());
	
	}
}
