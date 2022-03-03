package com.oracle.tour.service;



import com.oracle.tour.dto.Command;


/*
 * 이진호
 * 컨텐츠 댓글 관련 LJService 
 * */
public interface LJService {

	int WriteDetailCom(Command com);

	int deleteCommand(Command com);

	int updateCom(Command com);

	Command showReplyProperty(String com_no);

	int writeReply(Command com);

}
