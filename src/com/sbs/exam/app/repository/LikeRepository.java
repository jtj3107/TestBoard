package com.sbs.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.sbs.exam.app.dto.Article;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.util.Util;

public class LikeRepository {
	private List<Like> likes;
	private int lastId;

	public LikeRepository() {
		likes = new ArrayList<>();
		lastId = 0;
	}
	public int likeUpdate(int aritlceId, int loginedMemberId) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		

		Like like = new Like(id, regDate, updateDate, aritlceId, loginedMemberId, 1, 0);
		likes.add(like);

		lastId = id;

		return id;
	}
	public Like getLikeTrueOrFalse(int aritlceId, int loginedMemberId) {
		for(Like like : likes) {
			if(like.getArticleId() == aritlceId && like.getMemberId() == loginedMemberId) {
				return like;
			}
		}
		return null;
	}
	public void deleteLike(int id) {
		Like like = getLikeById(id);

		if (like != null) {
			likes.remove(like);
		}
		
	}
	private Like getLikeById(int id) {
		for(Like like : likes) {
			if(like.getId() == id) {
				return like;
			}
		}
		return null;
	}
	public int disLikeUpdate(int aritlceId, int loginedMemberId) {
		int id = lastId + 1;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		

		Like like = new Like(id, regDate, updateDate, aritlceId, loginedMemberId, 0, 1);
		likes.add(like);

		lastId = id;

		return id;
	}
}
