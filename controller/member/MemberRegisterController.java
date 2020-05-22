package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.member.MemberRegisterService;
import kr.co.foreignlove.service.member.MemberService;
import kr.co.foreignlove.vo.MemberVO;

public class MemberRegisterController implements BackController {
	private MemberService service;
	
	public MemberRegisterController() {
		service = new MemberRegisterService();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		JSONObject jObj = null;
		PrintWriter out = null;
		
		try
		{
			out = response.getWriter();
			service.doService(request, response);
			MemberVO member = (MemberVO) request.getAttribute("member");
			
			jObj = new JSONObject();
			
			if(member ==null) {
				jObj.put("result", true);
				
			}else {
				jObj.put("result", false);
			}
			jObj.put("message", "member fail");
		} catch (Exception e) {
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "member fail");
		}
		
		out.println(jObj);
	}

}
