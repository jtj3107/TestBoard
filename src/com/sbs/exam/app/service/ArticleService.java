package com.sbs.exam.app.service;

import java.util.List;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.repository.ArticleRepository;

public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService() {
		articleRepository = Container.getArticleRepository();
	}

	public int write(int boardId, int memberId, String title, String body, int hit) {
		return articleRepository.write(boardId, memberId, title, body, hit);
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public void deleteArticleById(int id) {
		articleRepository.deleteArticleById(id);
	}

	public List<Article> getFilterArticles(int boardId, String searchKeyword, String searchKeywordTypeCode, int page, int pageCount, String orderByColumn, String orderAscTypeCode) {
		List<Article> filteredKeywordArticles = articleRepository.filteredKeywordArticles(boardId, searchKeyword, searchKeywordTypeCode);
		List<Article> filteredPageArticles = articleRepository.filteredPageArticles(filteredKeywordArticles, page, pageCount);
		List<Article> orderArticles = articleRepository.orderArticles(filteredPageArticles, orderByColumn, orderAscTypeCode);
		
		return orderArticles;
	}

	public void makeTestData() {
		for (int i = 0; i < 100; i++) {
			String title = "제목 " + (i + 1);
			String body = "내용 " + (i + 1);
			write(i %2 +1, i %2 +1, title, body, (int)(Math.random()*100));
		}
	}
}
