package com.oracle.tour.dao;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.tour.dto.Command;


/*
 * 이진호
 * 컨텐츠 댓글 관련 LJDaoImpl 
 * -- MyBatis 
 * mapper 연결
 * */

@Repository
public class LJDaoImpl implements LJDao {
	
	private final SqlSession session;
	
	@Autowired
	public LJDaoImpl(SqlSession session) {
		this.session = session;
	}
	

	/*
	 * 컨텐츠 상세 페이지 댓글 작성 (insert)
	 * */
	@Override
	public int writeDetailCom(Command com) {
		System.out.println("LJDaoImpl writeDetailCom Start..");
		int result = 0;
		try {
			result = session.insert("LJWriteDetailCom",com);
		} catch (Exception e) {
			System.out.println("ShDaoImpl writeDetailCom ERROR -> "+e.getMessage());
		}
		return result;
	}
	/*
	 * 댓글삭제
	 * */
	@Override
	public int deleteCommand(Command com) {
		System.out.println("LJDaoImpl deleteCommand Start..");
		int result = 0;
		try {
			Command command = session.selectOne("LJSelectOneList",com);
			if(command.getCom_Step()==0) {
				result = session.delete("LJDeleteAllCommand",command);
			}else {
				result = session.delete("LJDeleteCommand",command);
			}
		} catch (Exception e) {
			System.out.println("LJDaoImpl deleteCommand ERROR -> "+e.getMessage());
		}
		return result;
	}
	
	/*
	 * 댓글 수정
	 * */
	@Override
	public int updateCom(Command com) {
		System.out.println("LJDaoImpl updateCom Start..");
		int result = 0;
		try {
			result = session.update("LJUpdateCommand",com);
			session.commit();
		} catch (Exception e) {
			System.out.println("LJDaoImpl updateCom ERROR -> "+e.getMessage());
		}
		return result;
	}

	/*
	 * 댓글 속성 보기
	 * */
	@Override
	public Command showReplyProperty(String com_no) {
		System.out.println("LJDaoImpl showReplyProperty Start..");
		Command com = null;
		try {
			com = session.selectOne("LJReplyProperty",com_no);
		} catch (Exception e) {
			System.out.println("LJDaoImpl showReplyProperty ERROR -> "+e.getMessage());
		}
		return com;
	}

	/*
	 * 대댓글 작성
	 * */
	@Override
	public int writeReply(Command com) {
		System.out.println("LJDaoImpl writeReply Start..");
		replyShape(com);
		int result = 0;
		try {
			result = session.insert("LJwriteReply",com);
		} catch (Exception e) {
			System.out.println("LJDaoImpl writeReply Error->"+e.getMessage());
		}
		return result;
	}
		/*
		 * 대댓글 그룹 확인
		 * */
		private void replyShape(Command com) {
			System.out.println("LJDaoImpl replyShape Start..");
			try {
				session.update("LJreplyShape",com);
			} catch (Exception e) {
				System.out.println("LJDaoImpl replyShape Error ->"+e.getMessage());
			}
		}

	
}
