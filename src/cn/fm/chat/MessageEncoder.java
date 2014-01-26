package cn.fm.chat;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import net.sf.json.JSONObject;

public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 将服务器端的消息实体对象自动转换成JSON，发送到客户端
	 */
	@Override
	public String encode(Message msg) throws EncodeException {
		return JSONObject.fromObject(msg).toString();
	}

}
