package cn.fm.bean.permissions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "wms_privilege")
public class Privilege {
	
	
	private int id;
	private String name;
	private String link;
	private Integer parentId;
	private String icon;
	private int orderNum;

	
	
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(length=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(length=50)
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Column(length=40)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Privilege) {
			Privilege m = (Privilege) obj;
			return m.getId() == this.getId();
		}
		return false;
	}

	public int hashCode() {
		return new Integer(id).hashCode();
	}

	public int compareTo(Privilege o) {
		return this.getOrderNum() > o.getOrderNum() ? 1
				: this.getOrderNum() < o.getOrderNum() ? -1 : 0;
	}
	
	
}
