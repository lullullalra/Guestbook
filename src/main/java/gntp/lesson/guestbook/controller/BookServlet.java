package gntp.lesson.guestbook.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gntp.lesson.guestbook.dao.GuestbookDAO;
import gntp.lesson.guestbook.vo.GuestbookVO;

public class BookServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//한글 처리 작업
		req.setCharacterEncoding("UTF-8");
		//명령어 처리 작업
		String command = req.getParameter("command");
		if(command==null) {
			command="list";
		}
		//기본 url 정보
		String url = "./guestbook/listBook.jsp";
		GuestbookDAO dao = new GuestbookDAO();
		if(command.equals("list")) {
			try {
				ArrayList<GuestbookVO> list = dao.selectAll();
				//req.setAttribute("list", list);
				HttpSession session = req.getSession();
				session.setAttribute("list", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("create")) {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String userId = req.getParameter("userId");
			GuestbookVO book = new GuestbookVO();
			book.setTitle(title);
			book.setContent(content);
			book.setUserId(userId);
			
			try {
				boolean flag = dao.insertOne(book);
				if(flag) {
					System.out.println("새글이 등록되었습니다.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "./BookServlet?command=list";
		} else if(command.equals("viewUpdateBook")) {
			// BookController로 인하여 더 이상 사용하지 않음
			String seq = req.getParameter("seq");
			GuestbookVO book = null;
			try {
				book = dao.selectOne(seq,null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("book", book);
			url = "./guestbook/viewUpdateBook.jsp";
		} else if(command.equals("update")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			System.out.println("update "+content);
			int readCount = Integer.parseInt(req.getParameter("readCount"));
			GuestbookVO book = new GuestbookVO();
			book.setTitle(title);
			book.setContent(content);
			book.setReadCount(readCount);
			book.setSeq(seq);
			
			
			try {
				boolean flag = dao.updateOne(book);
				if(flag) {
					System.out.println("글이 수정되었습니다.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "./BookServlet?command=list";
		} else if(command.equals("delete")) {
			String seq= req.getParameter("seq");
			try {
				boolean flag = dao.deleteOne(seq);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "./BookServlet?command=list";
		} else if(command.equals("viewWriteBook")) {
			url = "./guestbook/writeBook.jsp";
		}
		
		//forward코드
		//resp.sendRedirect("./guestbook/listBook.jsp");
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
		
	}
}
