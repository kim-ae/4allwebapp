package com.kimae.forallwebapp.configuration;

import java.util.Map;

public interface HtmlResponse {

    public String getView();
    public Map<String, Object> getModel();
}
