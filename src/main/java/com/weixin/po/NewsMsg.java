package com.weixin.po;

import java.util.List;

/**
 * @author	Lian
 * @time	2015年12月13日 下午1:59:53
 * @desc	图文消息对象
 */
public class NewsMsg extends BaseMsg {
	private int ArticleCount;
	private List<News> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> Articles) {
		this.Articles = Articles;
	}

}
