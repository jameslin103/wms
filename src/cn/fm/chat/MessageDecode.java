package cn.fm.chat;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import net.sf.json.JSONObject;

public class MessageDecode implements Decoder.Text<Message> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Message decode(String msg) throws DecodeException {
		JSONObject jsonObject = JSONObject.fromObject(msg);
		return (Message) JSONObject.toBean(jsonObject, Message.class);
	}

	@Override
	public boolean willDecode(String arg0) {
		return true;
	}

}
