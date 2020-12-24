package com.zyc.cloud.search.utils.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author ZengYc
 * @Date 2020/11/5 21:09
 */
@Data
public class BaseDo {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="update_time")
    private Date updateTime;

    @Column(name="operator")
    private String operator;

    private int page;

    private int size;

}
