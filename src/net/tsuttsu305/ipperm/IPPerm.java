package net.tsuttsu305.ipperm;

import java.util.ArrayList;
import java.util.List;
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
		this.logger.info("[IPPerm] IPPerm " + "Version " + pdfFile.getVersion() + " is Enabled");

		//イベント登録
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);

		getConfig().options().copyDefaults(true);
		saveConfig();
	}




	public void onDisable()
	{
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info("[IPPerm] IPPerm " + "Version " + pdfFile.getVersion() + " is Disabled");
	}

	public List<String>  getConfIP(Player p){
		String playerName = p.getName();
		List<String> ip = new ArrayList<String>();



		if (getConfig().isList(playerName)){		//Playerの名前のconfigがあった場合
			//debug code
			//getServer().broadcastMessage("Player ID Found");

			ip = getConfig().getStringList(playerName);


		}else{	//なかったら作成
			//debug code
			//getServer().broadcastMessage("Player ID not Found");

			ip.add("0.0.0.0");
			ip.add("example.com");
			getConfig().createSection(playerName);
			getConfig().set(playerName, ip);

		}
		saveConfig();
		return ip;
	}

	public List<String> getAdminList(){

		return getConfig().getStringList("adminList");

	}


}
