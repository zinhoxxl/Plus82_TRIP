package com.oracle.tour.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.tour.dto.Contents_like;
import com.oracle.tour.service.SHService;

@RestController
public class SHResponseController {
	
	private SHService shS;

	@Autowired
	public SHResponseController(SHService shS) {
		this.shS = shS;
	}
	// 컨텐츠 상세 페이지 좋아요
	@RequestMapping("/detail_Like")
	public String detailLike(Contents_like conLike) {
		System.out.println("SHResponseController detail_Like Start..");
		String resultStr = "";
		int result = 0;

		int likeCnt = shS.checkMember(conLike);
		System.out.println("likeCnt ->" + likeCnt);
		if (likeCnt == 1) {
			result = shS.updateInsertLike(conLike);
		} else {
			result = shS.detailLike(conLike);
		}
		resultStr = Integer.toString(result);
		return resultStr;
	}

	@RequestMapping("/ajaxLike")
	public String ajaxLike(String c_no, String m_id) {
		System.out.println("SHResponseController ajaxLike Start..");
		int result = shS.getMemberLikeCnt(c_no, m_id);
		String resultStr = Integer.toString(result);
		return resultStr;
	}

	@RequestMapping("/countLike")
	public String countLike(String c_no) {
		System.out.println("SHResponseController countLike Start..");
		int likeCnt = shS.getLikeCnt(c_no);
		String resultStr = Integer.toString(likeCnt);
		System.out.println(resultStr);
		return resultStr;
	}

	@RequestMapping("/cancel_Like")
	public String cancel_Like(Contents_like con) {
		System.out.println("SHResponseController cancel_Like Start..");
		String resultStr = "";
		int likeCnt = shS.checkMember(con);
		int result = 0;
		if (likeCnt == 1) {
			result = shS.updateLike(con);
		} else {
			result = shS.cancelLike(con);
		}
		resultStr = Integer.toString(result);
		return resultStr;
	}

	@RequestMapping("/ajaxCart")
	public String ajaxCart(String c_no, String m_id) {
		int cartCnt = shS.getCartCnt(c_no, m_id);
		System.out.println("cartCnt :" + cartCnt);
		String resultStr = Integer.toString(cartCnt);
		return resultStr;
	}

	@RequestMapping("/getCart")
	public String getCart(Contents_like con) {
		System.out.println("SHResponseController getCart Start..");
		String resultStr = "";
		int check = shS.checkMember(con);
		if (check == 0) {
			int result = shS.insertCart(con);
			resultStr = Integer.toString(result);
		} else {
			int result = shS.updateCart(con);
			resultStr = Integer.toString(result);
		}
		return resultStr;
	}

	@RequestMapping("/cancelCart")
	public String cancelCart(Contents_like con) {
		System.out.println("SHResponseController cancelCart Start..");
		int result = shS.deleteCart(con);
		String resultStr = Integer.toString(result);
		return resultStr;
	}

}
