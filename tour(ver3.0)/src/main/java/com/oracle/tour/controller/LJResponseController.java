package com.oracle.tour.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.tour.dto.Command;
import com.oracle.tour.service.LJService;
import com.oracle.tour.service.SHService;


/*
 *  이진호
 *  컨텐츠 댓글 관련 Response Controller  
 */

@RestController
// RestController : Controller + @ResponseBody => JSON 형태로 객체를 반환 하는 것이 목적
// 최근에 데이터를 응답으로 제공하는 Restful API를 개발 할 때 주로 사용한다.
public class LJResponseController {
	
	private LJService ljs;
	private SHService shS;

	@Autowired
	public LJResponseController(LJService ljs, SHService shS) {
		this.ljs = ljs;
		this.shS = shS;
	}

	/*
	 * 컨텐츠 상세 페이지에서 댓글 작성
	 * */
	@RequestMapping("/detailWriteCom")
	public String WriteDetailCom(Model model, String c_no, String m_id, String com_contents) {
		System.out.println("LJResponseController WriteDetailCom Start..");
		Command com = new Command();
		com.setC_no(Integer.parseInt(c_no));
		com.setM_id(m_id);
		com.setCom_contents(com_contents);
		int result = ljs.WriteDetailCom(com);
		String resultStr = Integer.toString(result);
		return resultStr;
	}

	/*
	 * 컨텐츠 상세 페이지에서 댓글 작성 후 리스트 뽑기
	 * */
	@RequestMapping("/ajaxCommandList")
	public List<Command> getAjaxCommandList(String c_no) {
		System.out.println("LJResponseController getAjaxCommandList Start..");
		List<Command> comList = shS.getCommandList(c_no);

		return comList;
	}

	/*
	 * 컨텐츠 상세 페이지 댓글 수정
	 * */
	@RequestMapping("/updateCom")
	public String updateCom(Command com) {
		System.out.println("LJResponseController updateCom Start..");
		int result = ljs.updateCom(com);
		String resultStr = Integer.toString(result);
		return resultStr;
	}

	/*
	 * 컨텐츠 상세 페이지 댓글 삭제
	 * */
	@RequestMapping("/deleteCom")
	public String deleteCommand(Command com) {
		System.out.println("LJResponseController deleteCommand Start..");
		int result = ljs.deleteCommand(com);
		
		
		System.out.println(result);
		String resultStr = Integer.toString(result);
		return resultStr;
	}

	/*
	 * 댓글 속성 불러오기
	 * */
	@RequestMapping("/showReplyProperty")
	public Command showReplyProperty(String com_no) {
		System.out.println("LJResponseController showReplyProperty Start..");
		Command com = ljs.showReplyProperty(com_no);
		System.out.println(com.getC_no());
		System.out.println(com.getCom_no());
		return com;
	}

	/*
	 * 대댓글 쓰기
	 * */
	@RequestMapping("/writeReply")
	public String writeReply(Command com) {
		System.out.println("LJResponseController writeReply Start..");
		int result = ljs.writeReply(com);
		System.out.println(result);
		String resultStr = Integer.toString(result);
		return resultStr;
	}
	
	/*
	 * 댓글 갯수 불러오기
	 * */
	@RequestMapping("/countCommandCnt")
	public String countCommandCnt(String c_no) {
		System.out.println("LJResponseController countCommandCnt Start..");
		int likeCnt = shS.getCommandCnt(c_no);
		String resultStr = Integer.toString(likeCnt);
		System.out.println(resultStr);
		return resultStr;
	}
}
