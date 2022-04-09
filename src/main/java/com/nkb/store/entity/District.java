package com.nkb.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

/** 省/市/区数据的实体类 */
@Data
@TableName("t_dict_district")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class District extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String parent;
    private String code;
    private String name;


}
