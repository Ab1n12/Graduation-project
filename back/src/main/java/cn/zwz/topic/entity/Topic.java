package cn.zwz.topic.entity;

import cn.zwz.basics.baseClass.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_topic")
@TableName("a_topic")
@ApiModel(value = "毕业设计课题")
public class Topic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课题名称")
    private String title;

    @ApiModelProperty(value = "课题类型")
    private String type;

    @ApiModelProperty(value = "课题难度")
    private String level;

    @ApiModelProperty(value = "选题要求")
    private String remark;

    @ApiModelProperty(value = "任务书")
    private String taskFile;

    @ApiModelProperty(value = "发布教师ID")
    private String teaId;

    @ApiModelProperty(value = "发布教师")
    private String teaName;

    @ApiModelProperty(value = "是否有剩余名额")
    private boolean checkFlag;

    @ApiModelProperty(value = "选题人ID")
    private String checkId;

    @ApiModelProperty(value = "选题人")
    private String checkName;

    @ApiModelProperty(value = "是否被审核")
    private boolean auditFlag;

    @ApiModelProperty(value = "剩余名额")
    private Integer quota;

    @ApiModelProperty(value = "是否被管理员审核")
    private boolean pass;

    public Boolean getPass() {
        return pass;
    }
}