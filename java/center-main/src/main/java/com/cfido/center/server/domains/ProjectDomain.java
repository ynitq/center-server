package com.cfido.center.server.domains;

import org.springframework.stereotype.Component;

import com.cfido.center.server.generated.ProjectDomain_CodeGen;

/**
 * <pre>
 * 操作Project表的domain, 
 * </pre>
 * 
 * @author 梁韦江 生成于 2016-12-19 17:42:34
 */
@Component
public class ProjectDomain extends ProjectDomain_CodeGen {

	@Override
	protected boolean isCacheDisable() {
		return true;
	}

}
