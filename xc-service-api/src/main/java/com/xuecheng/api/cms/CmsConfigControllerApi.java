package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "cms配置管理接口", description = "cms 配置管理结构，提供数据模型的管理，接口的查询")
public interface CmsConfigControllerApi {

    @ApiOperation("根据 ID 查询 CMS 配置信息")
    CmsConfig getmodel(String id);
}
