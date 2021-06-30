package com.sbs.exam.app.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sbs.exam.app.dto.Article;
import com.sbs.exam.util.Util;

public class ArticleRepository {
	private List<Article> articles;
	private int lastId;

	public ArticleRepository() {
		articles = new ArrayList<>();
		lastId = 0;
	}

	public int write(int boardId, int memberId, String title, String body, int hit, int like, int disLike, String keyword) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		Article article = new Article(id, regDate, updateDate, boardId, memberId, title, body, hit, like, disLike, keyword);
		articles.add(article);

		lastId = id;

		return id;
	}

	public void deleteArticleById(int id) {
		Article article = getArticleById(id);

		if (article != null) {
			articles.remove(article);
		}
	}

	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}

	public List<Article> filteredKeywordArticles(int boardId, String searchKeyword, String searchKeywordTypeCode) {
		if (boardId == 0 && searchKeyword.isEmpty()) {
			return articles;
		}
		List<Article> FilteredArticles = new ArrayList<>();

		for (Article article : articles) {
			if (article.getBoardId() != boardId && boardId != 0) {
				continue;
			}

			if (searchKeywordTypeCode.equals("body")) {
				if (article.getBody().contains(searchKeyword)) {
					FilteredArticles.add(article);
				}
			} else if(searchKeywordTypeCode.equals("keyword")) {
				if (article.getKeyword().contains(searchKeyword)) {
					FilteredArticles.add(article);
				}
			}
			else {
				if (article.getTitle().contains(searchKeyword)) {
					FilteredArticles.add(article);
				}
			}
		}
		return FilteredArticles;
	}

	public List<Article> filteredPageArticles(List<Article> filteredKeywordArticles, int page, int pageCount) {
		List<Article> FilteredArticles = new ArrayList<>();

		int fromIndex = (page - 1) * pageCount;
		int endIndex = filteredKeywordArticles.size() - 1 - fromIndex;
		int startIndex = endIndex + 1 - pageCount;

		if (startIndex < 0) {
			startIndex = 0;
		}

		for (int i = startIndex; i <= endIndex; i++) {
			Article article = filteredKeywordArticles.get(i);
			FilteredArticles.add(article);
		}

		return FilteredArticles;
	}

	public List<Article> orderArticles(List<Article> filteredPageArticles, String orderByColumn,
			String orderAscTypeCode) {
		System.out.println(orderByColumn);
		if (orderByColumn.isEmpty() && orderAscTypeCode.isEmpty()) {
			return filteredPageArticles;
		}

		if (orderByColumn.equals("id")) {
			if (orderAscTypeCode.equals("asc")) {
				Collections.sort(filteredPageArticles, Collections.reverseOrder());
			} else if (orderAscTypeCode.equals("desc")) {
				Collections.sort(filteredPageArticles);
			}
		} else if (orderByColumn.equals("hitCount")) {
			if (orderAscTypeCode.equals("asc")) {

				Comparator<Article> comparator = new Comparator<Article>() {

					@Override
					public int compare(Article p1, Article p2) {
						if (p1.getHit() > p2.getHit())
							return 1;
						// swap
						else if ((p1.getHit() == p2.getHit()) && (p1.getId() > p2.getId()))
							return 1;
						// swap
						else
							return -1;
						// no swap
					}
				};

				Collections.sort(filteredPageArticles, comparator);

				return filteredPageArticles;
			} else if (orderAscTypeCode.equals("desc")) {
				Comparator<Article> comparator = new Comparator<Article>() {

					@Override
					public int compare(Article p1, Article p2) {
						if (p2.getHit() > p1.getHit())
							return 1;
						// swap
						else if ((p2.getHit() == p1.getHit()) && (p2.getId() > p1.getId()))
							return 1;
						// swap
						else
							return -1;
						// no swap
					}
				};

				Collections.sort(filteredPageArticles, comparator);

				return filteredPageArticles;
			}
		} else if (orderByColumn.equals("likeCount")) {
			if (orderAscTypeCode.equals("asc")) {
				Comparator<Article> comparator = new Comparator<Article>() {

					@Override
					public int compare(Article p1, Article p2) {
						if (p1.getLike() > p2.getLike())
							return 1;
						// swap
						else if ((p1.getLike() == p2.getLike()) && (p2.getId() > p1.getId()))
							return 1;
						// swap
						else
							return -1;
						// no swap
					}
				};

				Collections.sort(filteredPageArticles, comparator);
				return filteredPageArticles;
			} else if (orderAscTypeCode.equals("desc")) {
				Comparator<Article> comparator = new Comparator<Article>() {

					@Override
					public int compare(Article p1, Article p2) {
						if (p2.getLike() > p1.getLike())
							return 1;
						// swap
						else if ((p2.getLike() == p1.getLike() && (p2.getId() > p1.getId())))
							return 1;
						// swap
						else
							return -1;
						// no swap
					}
				};

				Collections.sort(filteredPageArticles, comparator);
				return filteredPageArticles;
			}
		} else if (orderByColumn.equals("dislikeCount")) {
			if (orderAscTypeCode.equals("asc")) {
				Comparator<Article> comparator = new Comparator<Article>() {

					@Override
					public int compare(Article p1, Article p2) {
						if (p1.getDisLike() > p2.getDisLike())
							return 1;
						// swap
						else if ((p1.getDisLike() == p2.getDisLike()) && (p2.getId() > p1.getId()))
							return 1;
						// swap
						else
							return -1;
						// no swap
					}
				};

				Collections.sort(filteredPageArticles, comparator);
				return filteredPageArticles;
			} else if (orderAscTypeCode.equals("desc")){
				Comparator<Article> comparator = new Comparator<Article>() {

					@Override
					public int compare(Article p1, Article p2) {
						if (p2.getDisLike() > p1.getDisLike())
							return 1;
						// swap
						else if ((p2.getDisLike() == p1.getDisLike()) && (p1.getId() > p2.getId()))
							return 1;
						// swap
						else
							return -1;
						// no swap
					}
				};

				Collections.sort(filteredPageArticles, comparator);
				return filteredPageArticles;
			}

		}

		return filteredPageArticles;

	}
}