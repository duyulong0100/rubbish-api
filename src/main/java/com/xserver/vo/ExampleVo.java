package com.xserver.vo;

import com.xserver.dao.entity.Example;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("ExampleVo Class")
public class ExampleVo extends BaseVo {
    @ApiModelProperty(value = "标题")
    private String title;
    private String desc;

    public static ExampleVo covertDbtoVo(Example dbData) {
        ExampleVo exampleVo = new ExampleVo();
        exampleVo.setTitle(dbData.getTitle());
        exampleVo.setDesc(dbData.getDesc());
        return exampleVo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
