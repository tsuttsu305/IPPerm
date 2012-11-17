package jp.tsuttsu305;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerJoin implements Listener {
	public final static String AR = "\u002A";

	private IPPerm ipPerm = null;

	public PlayerJoin(IPPerm ipPerm) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.ipPerm =ipPerm;

	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event){
		//debug code
		//ipPerm.getServer().broadcastMessage("Join");

		Player joinPlayer = event.getPlayer();
		List<String> adminPlayers = ipPerm.getAdminList();
		
		boolean adminTF = false;
		for (String s:adminPlayers){
			//debug code
			//ipPerm.getServer().broadcastMessage(s + "J: " + joinPlayer.getName());
			
			if (s.equals(joinPlayer.getName())){
				adminTF = true;
				break;
			}
		}
		if (adminTF == true){
			//debug code
			//ipPerm.getServer().broadcastMessage("Adminlist Found");

			PermissionUser user = PermissionsEx.getUser(joinPlayer);
			String[] playerIP = joinPlayer.getAddress().getHostName().split("\\."); //JoinしたPlayerのIPアドレス取得 "."で分割
			String groupAdmin = ipPerm.getConfig().getString("adminGroup");	//Configからグループ取得 Admin
			String groupMember = ipPerm.getConfig().getString("memberGroup"); //Member
			List<String> confIP = ipPerm.getConfIP(joinPlayer); //Configのデータ取得

			//debug code
			//ipPerm.getServer().broadcastMessage("JoinIP: " + playerIP);

			boolean admin = false;

			for (String s:confIP){
				boolean[] perm = {false, false, false, false};
				//debug code
				//ipPerm.getServer().broadcastMessage(s);

				//Config側のアドレスを"."で分割
				String[] confIPs = s.split("\\.");

				//判定
				for(int i = 0;i<=3;i++)
				if (confIPs[i].equals(AR)){
					perm[i] = true;
				}else if(confIPs[i].equals(playerIP[i])){
					perm[i] = true;
				}

				//IPが一致した場合はAdmin権限を付与。
				if (perm[0] == true && perm[1] == true && perm[2] == true && perm[3] == true){
					//debug code
					//ipPerm.getServer().broadcastMessage("Admin");
					ipPerm.logger.info("[IPPerm] " + joinPlayer.getName() + " is " + groupAdmin + " Group Now!");

					String[] groups = {groupAdmin};
					user.setGroups(groups);
					admin = true;
					break;
					}
				}



			//一致がない場合はMember権限を付与
			if (!(admin)){
				//debug code
				//ipPerm.getServer().broadcastMessage("Member");
				ipPerm.logger.info("[IPPerm] " + joinPlayer.getName() + " is " + groupMember + " Group Now!");

				String[] groups = {groupMember};
				user.setGroups(groups);
			}
		}

		return;
	}

}
