package kr.co.foreignlove.service.boardfree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardDAO;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeUpdateService implements Service{

	BoardDAO dao;
	public BoardFreeUpdateService() {
		dao = (BoardFreeDAO)DAOManager.getDAO(BoardFreeDAO.NAME);
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int b_id = Integer.parseInt(request.getParameter("id"));
		
		if(title == null || title.trim().equals("")) {
			throw new Exception ("������ �Է��ϼ���.");
		}
		if(content == null || content.trim().equals("")) {
			throw new Exception ("������ �Է��ϼ���.");
		}
		
		BoardFreeVO board = new BoardFreeVO();
		board.setB_title(title);
		board.setB_content(content);
		board.setB_id(b_id);
		
		if(dao.update(board)) {
			request.setAttribute("board", board);
		}
		else {
			throw new Exception("�Խù� ������ �����Ͽ����ϴ�.");
		}
		
		
	}
	
}
