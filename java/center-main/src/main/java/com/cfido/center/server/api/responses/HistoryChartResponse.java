package com.cfido.center.server.api.responses;

import java.util.LinkedList;
import java.util.List;

import com.cfido.commons.beans.apiServer.BaseResponse;

/**
 * <pre>
 * 用于返回线图数据。数据格式是一个二维数组
 * 
 * 例如：
 * var pageviews = [
                    ["2017010-14:10", 1],
                    ["2017010-14:20", 2],
                    ]
 * </pre>
 * 
 * @author 梁韦江 2017年1月19日
 */
public class HistoryChartResponse extends BaseResponse {

	private List<List<Object>> data = new LinkedList<>();

	public List<List<Object>> getData() {
		return data;
	}

	public void setData(List<List<Object>> data) {
		this.data = data;
	}

}
