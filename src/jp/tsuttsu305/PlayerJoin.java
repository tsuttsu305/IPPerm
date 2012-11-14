package jp.tsuttsu305;

import java.util.List;

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
		//debug code
		ipPerm.getServer().broadcastMessage("Join");

		Player joinPlayer = event.getPlayer();
		PermissionUser user = PermissionsEx.getUser(joinPlayer);
		String playerIP = joinPlayer.getAddress().getHostName(); //JoinしたPlayerのIPアドレス取得
		String groupAdmin = ipPerm.getConfig().getString("adminGroup");	//Configからグループ取得 Admin
		String groupMember = ipPerm.getConfig().getString("memberGroup"); //Member

		List<String> confIP = ipPerm.getConfIP(joinPlayer); //Configのデータ取得
		//String[] ipList = ipPerm.getConfIP(joinPlayer);


		//debug code
		ipPerm.getServer().broadcastMessage("JoinIP: " + playerIP);

		String[] playerIPSp = playerIP.split("\\."); //PlayerのIPアドレスをオクテッ(ry
		boolean perm = false;

		for (String s:confIP){
			//debug code
			ipPerm.getServer().broadcastMessage(s);
			
			String[] confIPSp = s.split("\\."); //コンフィグのIPアドレスをオクテット単位でどーん

			//判定
			if(Integer.parseInt(confIPSp[0])== Integer.parseInt(playerIPSp[0])){
				if(Integer.parseInt(confIPSp[1])== Integer.parseInt(playerIPSp[1])){
					if(Integer.parseInt(confIPSp[2])== Integer.parseInt(playerIPSp[2])){
						if(Integer.parseInt(confIPSp[3])== Integer.parseInt(playerIPSp[3])){
							//debug code
							ipPerm.getServer().broadcastMessage("IP True");

							perm = true;
							break;
						}
					}
				}
			}
			

		}
		//IPが一致した場合はAdmin権限を付与。一致がない場合はMember権限を付与
		if (perm == true){
			//debug code
			ipPerm.getServer().broadcastMessage("Admin");

			String[] groups = {groupAdmin};
			user.setGroups(groups);
		}else{
			//debug code
			ipPerm.getServer().broadcastMessage("Member");

			String[] groups = {groupMember};
			user.setGroups(groups);
		}

		return;
	}

}
