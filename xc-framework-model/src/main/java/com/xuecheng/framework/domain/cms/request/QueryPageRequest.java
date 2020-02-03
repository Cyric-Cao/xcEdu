package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

/**
 * 页面查询模型
 */
@Data
public class QueryPageRequest extends RequestData {

    // 站点 Id
    private String siteId;
    // 页面 Id
    private String pageId;
    // 页面名称
    private String pageName;
    // 别名
    private String pageAliase;
    // 模板Id
    private String templateId;

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setPageAliase(String pageAliase) {
        this.pageAliase = pageAliase;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getPageId() {
        return pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public String getPageAliase() {
        return pageAliase;
    }

    public String getTemplateId() {
        return templateId;
    }
}
