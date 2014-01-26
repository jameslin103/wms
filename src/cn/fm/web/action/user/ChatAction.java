package cn.fm.web.action.user;

import java.util.ArrayList;
import java.util.List;
import cn.fm.bean.user.User;
import cn.fm.chat.ChatService;
import cn.fm.web.action.BaseAction;

public class ChatAction extends BaseAction{
	
	
	private static final long serialVersionUID = 7456979903129395604L;
	
	List<User>  onlineUser;
	
	
	public String getOnlineUsers() {
		onlineUser=new ArrayList<User>();
		onlineUser.addAll(ChatService.getOnlineUsers().keySet());
		
		return  SUCCESS;
	}


	

	public List<User> getOnlineUser() {
		return onlineUser;
	}



	public void setOnlineUser(List<User> onlineUser) {
		this.onlineUser = onlineUser;
	}

}
