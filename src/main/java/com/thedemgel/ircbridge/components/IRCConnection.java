
package com.thedemgel.ircbridge.components;

import com.thedemgel.ircbridge.pirclisteners.ReceiveMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import org.spout.api.Spout;
import org.spout.api.component.type.EntityComponent;
import org.spout.api.entity.Player;


public class IRCConnection extends EntityComponent {
	private PircBotX bot;
	private Channel channel;
	
	@Override
	public void onAttached() {
		Player p = (Player)getOwner();
		bot = new PircBotX();
		bot.getListenerManager().addListener(new ReceiveMessage(this));
		
                bot.setName(p.getDisplayName() + "-test");
		bot.setLogin(p.getName() + "-test");
		bot.setAutoNickChange(true);
		try {
			bot.connect("localhost", 6667, "demgelserver");
		} catch (IOException ex) {
			Spout.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
		} catch (IrcException ex) {
			Spout.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
		}
		channel = bot.getChannel("#pircbotx");
		bot.sendRawLine("VHOST none pass");
                bot.joinChannel(channel.getName());
		
	}
	
	public void sendMessage(String message) {
		channel.sendMessage(message);
	}
	
	public void disconnect() {
		bot.disconnect();
	}
}
