package jp.tsuttsu305;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IPPerm extends JavaPlugin {
	public static IPPerm plugin;

	Logger logger = Logger.getLogger("Minecraft");
	public void onEnable()
	{
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + "version" + pdfFile.getVersion() + " is Enabled");
		//イベント登録
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);

		getConfig().options().copyDefaults(true);
		saveConfig();




	}

	public void onDisable()
	{
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + "version" + pdfFile.getVersion() + " is Disabled");
	}

	public String getConfIP(Player p){
		String playerName = p.getName();
		String ip = "127.0.0.1";

		if (getConfig().isString(playerName)){		//Playerの名前のconfigがあった場合
			ip = getConfig().getString(playerName);

		}else{	//なかったら作成
			getConfig().createSection(playerName);
			getConfig().set(playerName, "0.0.0.0");
		}

		return ip;
	}
	

}
