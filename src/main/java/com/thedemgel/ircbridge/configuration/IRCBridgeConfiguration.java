
package com.thedemgel.ircbridge.configuration;

import com.thedemgel.ircbridge.IRCBridge;
import java.io.File;
import java.util.logging.Level;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;
import org.spout.api.util.config.ConfigurationHolder;

public class IRCBridgeConfiguration extends ConfigurationHolderConfiguration {

	// Messages
	public static final ConfigurationHolder SOME_SETTING = new ConfigurationHolder("SomeSetting", "some", "setting");

	public IRCBridgeConfiguration(File dataFolder) {
		super(new YamlConfiguration(new File(dataFolder, "config.yml")));
	}

	@Override
	public void load() {
		try {
			super.load();
			super.save();
		} catch (ConfigurationException e) {
			IRCBridge.getInstance().getLogger().log(Level.WARNING, "Error loading IRCBridge configuration: ", e);
		}
	}

	@Override
	public void save() {
		try {
			super.save();
		} catch (ConfigurationException e) {
			IRCBridge.getInstance().getLogger().log(Level.WARNING, "Error saving IRCBridge configuration: ", e);
		}
	}
}
