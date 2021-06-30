package com.sbs.exam.app.controller;

import java.util.Scanner;

import com.sbs.exam.app.Rq;
import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.app.service.ArticleService;
import com.sbs.exam.app.service.LikeService;
import com.sbs.exam.app.service.MemberService;

public class UsrLikeController extends Controller {

	private LikeService likeService;
	private Scanner sc;
	private ArticleService articleService;
	private MemberService memberService;

	public UsrLikeController() {
		likeService = Container.getLikeService();
		sc = Container.getSc();
		articleService = Container.getArticleService();
		memberService = Container.getMemberService();

	}

	@Override
	public void performAction(Rq rq) {
		if (rq.getActionPath().equals("/usr/like/like")) {
			actionLike(rq);
		} else if (rq.getActionPath().equals("/usr/like/cancelLike")) {
			actionLike(rq);
		} else if (rq.getActionPath().equals("/usr/like/disLike")) {
			actionDisLike(rq);
		} else if (rq.getActionPath().equals("/usr/like/cancelDislike")) {
			actionDisLike(rq);
		}
	}

	private void actionDisLike(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("게시물을 선택해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}
		
		int loginedMemberId = rq.getLoginedMemberId();

		Like like = likeService.getLikeTrueOrFalse(article.getId(), loginedMemberId);
		
		if(like == null) {
			article.setDisLike(article.getDisLike() + 1);
			
			likeService.disLikeUpdate(article.getId(), loginedMemberId);

			System.out.println("싫어요");
			return;
		}
		
		if(like.getLike() == 1 && like != null) {
			System.out.println("이미 좋아요 하였습니다.");
			return;
		}
		
		if (like.getDisLike() == 1 && like != null) {
			article.setDisLike(article.getDisLike() - 1);
			likeService.deleteLike(like.getId());
			System.out.println("싫어요 취소");
			like.setDisLike(0);
			return;
		}
	}

	private void actionLike(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			System.out.println("게시물을 선택해주세요.");
			return;
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}

		int loginedMemberId = rq.getLoginedMemberId();

		Like like = likeService.getLikeTrueOrFalse(article.getId(), loginedMemberId);
		
		if(like == null) {
			article.setLike(article.getLike() + 1);

			likeService.likeUpdate(article.getId(), loginedMemberId);

			System.out.println("좋아요");
			return;
		}
		
		if(like.getDisLike() == 1) {
			System.out.println("이미 싫어요 하였습니다.");
			return;
		}
		
		if (like.getLike() == 1 ) {
			article.setLike(article.getLike() - 1);
			likeService.deleteLike(like.getId());
			System.out.println("좋아요 취소");
			like.setLike(0);
			return;
		}
	}
}
