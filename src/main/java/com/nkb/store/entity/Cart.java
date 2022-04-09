package com.nkb.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Data
@TableName("t_cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity implements Serializable {

    @TableId(value = "cid",type = IdType.AUTO)
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;


}
