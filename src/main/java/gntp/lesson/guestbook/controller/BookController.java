package gntp.lesson.guestbook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gntp.lesson.guestbook.dao.GuestbookDAO;
import gntp.lesson.guestbook.vo.GuestbookVO;
import gntp.lesson.guestbook.vo.ReplyVO;

@WebServlet("*.do")
public class BookController extends HttpServlet{
	//기본 4개 메소드 코드 작성
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	private void test() {
//		String url = req.getRequestURL().toString();
//		String context = req.getContextPath();
//		int len = context.length();
//		String uri2 = uri.substring(len+1);
//		out.print("<h1>hi servlet</h1>");
//		out.print("<h1> uri = "+uri+"</h1>");
//		out.print("<h1> uri1 = "+uri1+"</h1>");
//		out.print("<h1> uri2 = "+uri2+"</h1>");
//		out.print("<h1> url = "+url+"</h1>");
//		out.print("<h1> context path = "+context+"</h1>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("doPost");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String uri = req.getRequestURI();
		String[] temp = uri.split("/");
		String command = temp[temp.length-1];
		
		//기본 url 정보
		String url = "./guestbook/listBook.jsp";
		GuestbookDAO dao = new GuestbookDAO();
		if(command.equals("list.do")) {
			try {
				ArrayList<GuestbookVO> list = dao.selectAll();
				//req.setAttribute("list", list);
				HttpSession session = req.getSession();
				session.setAttribute("list", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("create.do")) {
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
			url = "list.do";
		} else if(command.equals("viewUpdateBook.do")) {
			String seq = req.getParameter("seq");
			GuestbookVO book = null;
			try {
				book = dao.selectOneForUpdate(seq);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("book", book);
			url = "./guestbook/viewUpdateBook.jsp";
		} else if(command.equals("update.do")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			//System.out.println("update "+content);
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
			url = "list.do";
		} else if(command.equals("delete.do")) {
			String seq= req.getParameter("seq");
			try {
				boolean flag = dao.deleteOne(seq);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "list.do";
		} else if(command.equals("viewWriteBook.do")) {
			url = "./guestbook/writeBook.jsp";
		} else if(command.equals("read.do")) {
			String seq = req.getParameter("seq");
			String token = req.getParameter("token");
			GuestbookVO book = null;
			try {
				book = dao.selectOne(seq,token);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("book", book);
			url = "./guestbook/read.jsp";
		} else if(command.equals("writeReply.do")) {
			String seq = req.getParameter("seq");
			String content = req.getParameter("reply");
			ReplyVO vo = new ReplyVO();
			vo.setSeq(Integer.parseInt(seq));
			vo.setContent(content);
			try {
				boolean flag = dao.insertReply(vo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			url = "read.do?seq="+seq;
		}
		
		//forward코드
		//resp.sendRedirect("./guestbook/listBook.jsp");
		//if(!command.equals("list.do")) {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
		//} else {
			//resp.sendRedirect(url);
		//}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//System.out.println("init");
	}

	
}
