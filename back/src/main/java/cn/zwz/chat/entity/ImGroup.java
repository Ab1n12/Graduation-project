package cn.zwz.chat.entity;

import javax.persistence.*;

/**
 * 群组聊天
 */
@Table(name = "im_group")
public class ImGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/** 内容 */
	@Column(name = "content")
	private String content;
	/** 用户名 */
	@Column(name = "name")
	private String name;
	/** 头像 */
	@Column(name = "avatar")
	private String avatar;
	/** 时间 */
	@Column(name = "time")
	private String time;
	/** 类型 */
	@Column(name = "type")
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
