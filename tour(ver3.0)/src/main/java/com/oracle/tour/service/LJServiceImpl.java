package com.oracle.tour.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.LJDao;
import com.oracle.tour.dto.Command;


/*
 * 이진호
 * 컨텐츠 댓글 관련 LJServiceImpl
 * */

@Service
@Transactional
public class LJServiceImpl implements LJService {
	
	private final LJDao ljDao;
	@Autowired
	public LJServiceImpl(LJDao ljDao) {
		this.ljDao = ljDao;
	}
	
	@Override
	public int WriteDetailCom(Command com) {
		System.out.println("LJServiceImpl WriteDetailCom Start..");
		int result = ljDao.writeDetailCom(com);
		return result;
	}
	@Override
	public int deleteCommand(Command com) {
		System.out.println("LJServiceImpl deleteCommand Start..");
		int result = ljDao.deleteCommand(com);
		return result;
	}
	@Override
	public int updateCom(Command com) {
		System.out.println("LJServiceImpl updateCom Start..");
		int result = ljDao.updateCom(com);
		return result;
	}
	@Override
	public Command showReplyProperty(String com_no) {
		System.out.println("LJServiceImpl showReplyProperty Start..");
		Command com = ljDao.showReplyProperty(com_no);
		return com;
	}
	@Override
	public int writeReply(Command com) {
		System.out.println("LJServiceImpl writeReply Start..");
		int result = ljDao.writeReply(com);
		return result;
	}

}
