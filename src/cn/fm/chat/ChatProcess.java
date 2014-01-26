package cn.fm.chat;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat.do/{userId}", encoders = MessageEncoder.class, decoders = MessageDecode.class)
public class ChatProcess {
	public ChatProcess() {
		System.out.println("实例化ChatProcess");
	}

	@OnOpen
	public void open(Session session, EndpointConfig config,
			@PathParam("userId") String userId) {
		ChatService.addOnlineUser(userId, session);
	}

	@OnClose
	public void close(Session session, CloseReason reason,
			@PathParam("userId") String userId) {
		ChatService.removeUser(userId, session);
	}

	@OnMessage
	 public void message(Session session,Message message) {
		 ChatService.sendMsg(session, message);
	 }
	// @OnError
	// public void error() {
	//
	// }
}
