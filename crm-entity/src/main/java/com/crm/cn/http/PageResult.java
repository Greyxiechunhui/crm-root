package com.crm.cn.http;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResult {

    private Object recerds;

    private Long total;

    public static PageResult instance(Object records,long total){
        return new PageResult(records,total);
    }

}
