package com.sbs.exam.app.service;

import com.sbs.exam.app.container.Container;
import com.sbs.exam.app.dto.Like;
import com.sbs.exam.app.repository.LikeRepository;

public class LikeService {
	private LikeRepository likeRepository;

	public LikeService() {
		likeRepository = Container.getLikeRepository();
	}

	public int likeUpdate(int aritlceId, int loginedMemberId) {
		return likeRepository.likeUpdate(aritlceId, loginedMemberId);
	}
	
	public int disLikeUpdate(int aritlceId, int loginedMemberId) {
		return likeRepository.disLikeUpdate(aritlceId, loginedMemberId);
	}

	public Like getLikeTrueOrFalse(int aritlceId, int loginedMemberId) {
		return likeRepository.getLikeTrueOrFalse(aritlceId, loginedMemberId);
	}

	public void deleteLike(int id) {
		likeRepository.deleteLike(id);
	}

}
