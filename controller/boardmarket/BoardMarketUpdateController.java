package kr.co.foreignlove.controller.boardmarket;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardmarket.BoardMarketUpdateService;

public class BoardMarketUpdateController implements BackController{
	Service service;
	public BoardMarketUpdateController() {
		service = new BoardMarketUpdateService();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = null;
		try {
			service.doService(request, response);
			obj = new JSONObject();
			obj.put("result", true);
			obj.put("message", "�Խù������� ���������� ó���Ǿ����ϴ�.");
		}
		catch(Exception e) {
			obj = new JSONObject();
			obj.put("result", false);
			obj.put("message", e.getMessage());
		}
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.print(obj.toJSONString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
