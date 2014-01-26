package cn.fm.chat;

/**
 * 消息实体类
 * 
 * @author tanlan
 * 
 */
public class Message {
	private String fromUserId;
	private String fromUserAccount;
	private String toUserId;
	private String toUserAccount;
	private String msg;
	private String sendTime;
	private String type;

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserAccount() {
		return fromUserAccount;
	}

	public void setFromUserAccount(String fromUserAccount) {
		this.fromUserAccount = fromUserAccount;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUserAccount() {
		return toUserAccount;
	}

	public void setToUserAccount(String toUserAccount) {
		this.toUserAccount = toUserAccount;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
