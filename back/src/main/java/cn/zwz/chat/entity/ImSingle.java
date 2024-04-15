package cn.zwz.chat.entity;

import javax.persistence.*;

/**
 * 单人聊天
 */
@Table(name = "im_single")
public class ImSingle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/** 内容 */
	@Column(name = "content")
	private String content;
	/** 发送人 */
	@Column(name = "fromuser")
	private String fromuser;
	/** 发送人头像 */
	@Column(name = "fromavatar")
	private String fromavatar;
	/** 时间 */
	@Column(name = "time")
	private String time;
	/** 类型 */
	@Column(name = "type")
	private String type;
	/** 接收人 */
	@Column(name = "touser")
	private String touser;
	/** 接收人头像 */
	@Column(name = "toavatar")
	private String toavatar;
	/** 是否已读 */
	@Column(name = "readed")
	private Integer readed;

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

	public String getFromuser() {
		return fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

	public String getFromavatar() {
		return fromavatar;
	}

	public void setFromavatar(String fromavatar) {
		this.fromavatar = fromavatar;
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

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToavatar() {
		return toavatar;
	}

	public void setToavatar(String toavatar) {
		this.toavatar = toavatar;
	}

	public Integer getReaded() {
		return readed;
	}

	public void setReaded(Integer readed) {
		this.readed = readed;
	}
}
