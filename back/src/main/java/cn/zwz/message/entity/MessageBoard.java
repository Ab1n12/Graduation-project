package cn.zwz.message.entity;

import cn.zwz.basics.baseClass.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_message_board")
@TableName("a_message_board")
@ApiModel(value = "留言板")
public class MessageBoard extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "留言人ID")
    private String userId;

    @ApiModelProperty(value = "留言人")
    private String userName;

    @ApiModelProperty(value = "留言时间")
    private String date;

    @Length(max = 1024)
    @ApiModelProperty(value = "留言内容")
    private String content;

    @ApiModelProperty(value = "答复ID")
    private String replyId;
}
