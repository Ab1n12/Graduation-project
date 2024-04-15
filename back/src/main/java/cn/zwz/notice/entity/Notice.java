package cn.zwz.notice.entity;

import cn.zwz.basics.baseClass.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Date;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_notice")
@TableName("a_notice")
@ApiModel(value = "公告")
public class Notice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告标题")
    private String title;

    @ApiModelProperty(value = "公告负责人Id")
    private String dutyId;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "公告负责人")
    private String dutyName;

    @ApiModelProperty(value = "公告内容")
    private String remark;

    @ApiModelProperty(value = "是否公开")
    private boolean open;

}