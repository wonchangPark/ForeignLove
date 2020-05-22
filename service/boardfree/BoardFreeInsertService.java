package kr.co.foreignlove.service.boardfree;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.AttachedDAO;
import kr.co.foreignlove.dao.BoardDAO;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.dao.BoardTypeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.AttachedVO;
import kr.co.foreignlove.vo.BoardFreeVO;
import kr.co.foreignlove.vo.BoardTypeVO;
import kr.co.foreignlove.vo.MemberVO;

public class BoardFreeInsertService implements Service{
	BoardDAO dao;
	AttachedDAO adao;
	public BoardFreeInsertService() {
		dao =(BoardDAO)(DAOManager.getDAO(BoardFreeDAO.NAME));
		adao = (AttachedDAO)(DAOManager.getDAO(AttachedDAO.NAME));
	}
	public void doService(HttpServletRequest request, HttpServletResponse respobnse) throws Exception{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String[] files =request.getParameterValues("files");
		
		String path = request.getServletContext().getRealPath("/uploadtemp");
		String pathOrig = request.getServletContext().getRealPath("/uploads");
		File uploadtemp = new File(path);
		File move_dir = new File(pathOrig);
		
		
		
		//���⿡ ���Ϲ迭�� Stirng[]�� ���� ���� (���⿡ ���� �̹������� ������ �ƴ� �̸��� �������� uploadtemp�� ���� 
		//�� �̸���� ��ġ�ϴ� image���ϵ��� db�� �־��ش�. 
		//File forder = new File(path); �� �ؼ� path�� uploads������ ��θ� �ִ´�.
		//�� �� File�� �޼ҵ带 �ڹٿ��� �����ϰ� list()�� String[]���� �̾ƿͼ� ���� �迭�� ������ Ȯ���Ŀ� �����Ѱ͸� db�� ������ ���.
		
		if(title == null || title.trim().equals("")) {
			throw new Exception ("������ �Է��ϼ���.");
		}
		if(content == null || content.trim().equals("")) {
			throw new Exception ("������ �Է��ϼ���.");
		}
		
		BoardTypeVO bt = new BoardTypeVO();		
		bt = ((BoardTypeDAO)(DAOManager.getDAO(BoardTypeDAO.NAME))).getBoardTypeVO("FR");
		BoardFreeVO board = new BoardFreeVO();
		board.setB_title(title);
		board.setB_content(content);
		
		board.setBoardType(bt);
		MemberVO member = new MemberVO();
		//session�� member�� �־���� :
		HttpSession session = request.getSession();
		
		member = (MemberVO)session.getAttribute("loginUserInfo");
		
		board.setMember(member);
		dao.insert(board);
//		if(dao.insert(board)) {
//			request.setAttribute("board", board);
//		}
//		else {
//			throw new Exception("�Խù���Ͽ� �����Ͽ����ϴ�.");
//		}
		
		//attached �ִ°�
		
		AttachedVO attached = new AttachedVO();
		
		for(int i =1 ; i< files.length; i++) {
			
			for(int j=0; j< uploadtemp.list().length ; j++) {
				
				if(uploadtemp.list()[j].equals(files[i])) {
					System.out.println("---------------------");
					System.out.println(files[i]);
					System.out.println(uploadtemp.listFiles()[j]);
					System.out.println("����");
					
					File moveFile = new File(move_dir, uploadtemp.listFiles()[j].getName());
					uploadtemp.listFiles()[j].renameTo(moveFile);
					
					attached.setA_name(files[i]);
					attached.setB_id(board.getB_id());
					attached.setBoardType(bt);
					
					boolean result = adao.insert(attached);
					System.out.println(result);
					
					board.setAttached(attached);
					System.out.println(board.getAttachedList().get(0));
					System.out.println("---------------------");
				}
			}
			
		}
		
		//request.setAttribute("board", board);
		//the reason why using session is that request cannot maintain information we have.
		
	}
}
