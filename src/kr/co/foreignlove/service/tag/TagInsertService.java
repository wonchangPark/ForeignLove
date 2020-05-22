package kr.co.foreignlove.service.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.TagDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.TagVO;

public class TagInsertService implements Service
{
	private TagDAO dao;

	public TagInsertService()
	{
		dao = (TagDAO) DAOManager.getDAO(TagDAO.NAME);
	}

	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//String t_name = request.getParameter("t_name");

		String tags[] = request.getParameterValues("tags");
		String strB_id = request.getParameter("b_id");
		int b_id = Integer.parseInt(strB_id);
		
		for(String t_name: tags)
		{
			if (t_name == null || t_name.trim().equals(""))
			{
				throw new Exception("�±׸��� �Էµ��� �ʾҽ��ϴ�.");
			}
			
			TagVO tag = new TagVO();
			
			tag.setT_name(t_name);
			
			System.out.println(b_id);
			if (dao.insert(tag, b_id))
			{
				request.setAttribute("tag", tag);
			} else
			{
				throw new Exception("�±� ��Ͽ� �����Ͽ����ϴ�.");
			}
		}

	}
}