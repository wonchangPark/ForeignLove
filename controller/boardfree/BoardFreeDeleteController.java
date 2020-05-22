package kr.co.foreignlove.controller.boardfree;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardfree.BoardFreeDeleteService;

public class BoardFreeDeleteController implements BackController {
	Service service;
	public BoardFreeDeleteController() {
		service = new BoardFreeDeleteService();
	}
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response ) {
		JSONObject obj = null;
		PrintWriter out = null;
		try {
			service.doService(request, response);
			obj = new JSONObject();
			obj.put("result", true);
			obj.put("message", "��û������������ ó���Ǿ����ϴ�.");
		}
		catch(Exception e) {
			obj = new JSONObject();
			obj.put("result", false);
			obj.put("message", "�Խñ� ������ �����Ͽ����ϴ�...");
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.print(obj.toJSONString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
