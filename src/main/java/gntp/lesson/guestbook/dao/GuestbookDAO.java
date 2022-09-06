package gntp.lesson.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gntp.lesson.guestbook.util.ConnectionManagerV2;
import gntp.lesson.guestbook.vo.GuestbookVO;
import gntp.lesson.guestbook.vo.ReplyVO;

public class GuestbookDAO {
	public boolean insertOne(GuestbookVO book) throws SQLException {
		boolean flag = false;
		//connection 연결확인 하세요
		Connection con = ConnectionManagerV2.getConnection();
		String sql = "insert into guestbook(title,content,user_id) values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getTitle());
		pstmt.setString(2, book.getContent());
		pstmt.setString(3, book.getUserId());
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return flag;
	}
	
	public ArrayList<GuestbookVO> selectAll() throws SQLException{
		ArrayList<GuestbookVO> list = null;
		Connection con = ConnectionManagerV2.getConnection();
		String sql = "select * from guestbook";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		list = new ArrayList<GuestbookVO>();
		GuestbookVO book = null;
		while(rs.next()) {
			book = new GuestbookVO(rs.getInt(1),rs.getString(2),rs.getString(3),
									rs.getString(4),rs.getTimestamp(5),rs.getInt(6));
			list.add(book);
		}
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return list;
	}

	public GuestbookVO selectOneForUpdate(String seq) throws SQLException {
		// TODO Auto-generated method stub
		GuestbookVO book = null;
		Connection con = ConnectionManagerV2.getConnection();
		String sql = "select * from guestbook where seq = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,Integer.parseInt(seq));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			//System.out.println(rs.getString(4));
			book = new GuestbookVO(rs.getInt(1),rs.getString(2),rs.getString(3),
									rs.getString(4),rs.getTimestamp(5),rs.getInt(6)+1);
		}
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return book;
	}
	
	public GuestbookVO selectOne(String seq,String token) throws SQLException {
		// TODO Auto-generated method stub
		GuestbookVO book = null;
		Connection con = ConnectionManagerV2.getConnection();
		// 조회수 업데이트 실시--list에서 요청할 때에만 적용되어야 함
		String sql = null;
		PreparedStatement pstmt = null;
		if(token!=null) {
			sql = "update guestbook set read_count = read_count + 1 where seq = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(seq));
			int affectedCount = pstmt.executeUpdate();
			boolean flag = false;
			if(affectedCount>0) {
				flag = true;
			}
		}
		// 해당글 조회
		sql = "select * from guestbook g left join reply r on g.seq = r.seq  where g.seq = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,Integer.parseInt(seq));
		ResultSet rs = pstmt.executeQuery();
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO vo = null;
		while(rs.next()) {
			//System.out.println(rs.getString(4));
			if(book==null) {
				book = new GuestbookVO(rs.getInt(1),rs.getString(2),rs.getString(3),
									rs.getString(4),rs.getTimestamp(5),rs.getInt(6));
			}
			//System.out.println("null is "+rs.getString(9));
			if(rs.getString(9)!=null) {
				vo = new ReplyVO(rs.getInt(7),rs.getString(8),rs.getTimestamp(9),rs.getInt(10));
				list.add(vo);
			} else {
				list = null;
				break;
			}
		}
		book.setReplyList(list);
		
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return book;
	}

	public boolean updateOne(GuestbookVO book) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		//connection 연결확인 하세요
		Connection con = ConnectionManagerV2.getConnection();
		String sql = "update guestbook set title = ?, content=?, read_count=? where seq = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getTitle());
		//System.out.println("dao "+book.getContent());
		pstmt.setString(2, book.getContent());
		pstmt.setInt(3, book.getReadCount());
		pstmt.setInt(4, book.getSeq());
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return flag;
	}

	public boolean deleteOne(String seq) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		//connection 연결확인 하세요
		Connection con = ConnectionManagerV2.getConnection();
		String sql = "delete from guestbook where seq = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(seq));
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return flag;
	}

	public boolean insertReply(ReplyVO vo) throws SQLException {
		// TODO Auto-generated method stub
		boolean flag = false;
		//connection 연결확인 하세요
		Connection con = ConnectionManagerV2.getConnection();
		String sql = "insert into reply(content,seq) values (?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setInt(2, vo.getSeq());
		int affectedCount = pstmt.executeUpdate();
		if(affectedCount>0) {
			flag = true;
		}
		ConnectionManagerV2.closeConnection(null, pstmt, con);
		return flag;
	}
}







