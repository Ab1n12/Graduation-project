package cn.zwz.data.entity;

import cn.zwz.basics.baseClass.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_dict")
@TableName("a_dict")
@ApiModel(value = "数据字典")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据字典标题")
    private String title;

    @ApiModelProperty(value = "数据字典排序值")
    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "数据字典备注")
    private String description;

    @ApiModelProperty(value = "数据字典类型")
    private String type;
}