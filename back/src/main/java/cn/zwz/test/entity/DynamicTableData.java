package cn.zwz.test.entity;

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
@Table(name = "a_dynamic_table_data")
@TableName("a_dynamic_table_data")
@ApiModel(value = "动态表格值")
public class DynamicTableData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "动态表格值标题")
    private String title;

}