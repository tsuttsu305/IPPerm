package jp.tsuttsu305;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerJoin implements Listener {

	private IPPerm ipPerm = null;

	public PlayerJoin(IPPerm ipPerm) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.ipPerm =ipPerm;

	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player joinPlayer = event.getPlayer();
		PermissionUser user = PermissionsEx.getUser(joinPlayer);
		String playerIP = joinPlayer.getAddress().getHostName();
		
		
		
		
		return;
	}

}
