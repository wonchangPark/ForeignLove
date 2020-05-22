package kr.co.foreignlove.controller.tag;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.tag.TagViewService;
import kr.co.foreignlove.vo.TagVO;

public class TagViewController implements BackController
{
	private Service service;
	
	public TagViewController()
	{
		service = new TagViewService();
	}
	
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject jObj = null;
		@SuppressWarnings("unused")
		PrintWriter out = null;
		
		try
		{
			service.doService(request, response);
			TagVO tag = (TagVO)request.getAttribute("tag");
			
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "��û�� ���������� ó���Ǿ����ϴ�.");
			jObj.put("tag", tag.convertMap());
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "��û ó���� �����Ͽ����ϴ�.");
		}
	}
}