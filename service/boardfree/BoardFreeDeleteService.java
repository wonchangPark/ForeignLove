package kr.co.foreignlove.service.boardfree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardDAO;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeDeleteService implements Service{
	BoardDAO dao;
	BoardFreeVO board = null;
	public BoardFreeDeleteService() {
		dao = (BoardDAO)(DAOManager.getDAO(BoardFreeDAO.NAME));
	}
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int id;
		id =Integer.parseInt((request.getParameter("id")));
		//board���� �������� ����ߴٸ� ���� ���� board�� �����;��ϴµ� ��� �����ñ�
		boolean result = dao.delete(id);
		if(!result) {
			throw new Exception ("�Խñ� ������ �����Ͽ����ϴ�.");
		}
		
	}
}
