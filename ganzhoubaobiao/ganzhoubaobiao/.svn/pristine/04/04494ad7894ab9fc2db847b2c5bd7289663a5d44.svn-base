package com.zklcsoftware.basic.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author audin
 *
 */
@Component
@ConfigurationProperties(prefix="nativesql")
public class SqlMap {
	private Map<String, String> sqls = new HashMap<>();
	
	public Map<String, String> getSqls() {
        return sqls;
    }

    public void setSqls(Map<String, String> sqls) {
        this.sqls = sqls;
    }
}
