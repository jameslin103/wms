package cn.fm.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.user.User;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.service.user.UserService;
import cn.fm.utils.WmsUtil;
import cn.fm.web.action.InitServlet;



public class ChatService {
	
	//存放在线用户
	private static Map<User, Session> onlineUsers = new HashMap<User, Session>();

	private static UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext  axt=new ClassPathXmlApplicationContext("beans.xml");
		userService=(UserService)axt.getBean("userServiceImpl");	
		
	}
	
	public static Map<User, Session> getOnlineUsers() {
		return onlineUsers;
	}
	
	public static void addOnlineUser(String userId,Session session){
		User user=userService.getById(userId);
		onlineUsers.put(user, session);
		
		Message msg=new Message();
		msg.setFromUserId(userId);
		msg.setFromUserAccount(user.getAccount());
		msg.setType("system");
		msg.setMsg("on");
		
		sendMsg(session,msg);
	}
	
	public static void removeUser(String userId,Session session){
		User user=userService.getById(userId);
		onlineUsers.remove(user);
		
		Message msg=new Message();
		msg.setFromUserId(userId);
		//msg.setFromUserAccount(user.getAccount());
		msg.setType("system");
		msg.setMsg("off");
		
		sendMsg(session,msg);
	}
	
	public static void sendMsg(Session session,Message message){
		Set<Session> sessions=session.getOpenSessions();
		if(message.getType().equals("system")){
			for (Session s : sessions) {
					try {
						s.getBasicRemote().sendObject(message);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (EncodeException e) {
						e.printStackTrace();
					}
			}
		}else{
			message.setSendTime(WmsUtil.getNow());
			String toUserId=message.getToUserId();
			User user=userService.getById(toUserId);
			Session s=onlineUsers.get(user);
				try {
					s.getBasicRemote().sendObject(message);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (EncodeException e) {
					e.printStackTrace();
				}
		
		
		}
	}
	
}
