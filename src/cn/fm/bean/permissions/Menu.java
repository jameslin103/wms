package cn.fm.bean.permissions;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Menu implements Serializable {

	private static final long serialVersionUID = 7829857476567874075L;

	@Id
	@GeneratedValue
	private Long menuId;

	@ManyToOne
	@JoinColumn(name = "pmenuId")
	private Menu parentMenu;

	@Column(length=255)
	private String name;

	@Column(length=255)
	private String url;
	
	@Column(length=2)
	private Integer type;

	
	
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
