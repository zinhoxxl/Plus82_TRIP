package com.oracle.tour.dao;



import com.oracle.tour.dto.Command;

/*
 * 이진호
 * 컨텐츠 댓글 관련 LJDao 
 * */

public interface LJDao {

	int writeDetailCom(Command com);

	int deleteCommand(Command com);

	int updateCom(Command com);

	Command showReplyProperty(String com_no);

	int writeReply(Command com);

}
