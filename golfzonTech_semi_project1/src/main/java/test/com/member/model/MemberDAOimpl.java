package test.com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAOimpl implements MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDAOimpl() {
		System.out.println("MemberDAOimpl()..");

		try {
			Class.forName(MemberDB_oracle.DRIVER_NAME);
			System.out.println("Driver successed..");

//			jdbcConnectionTest();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}// end constructor

	private void jdbcConnectionTest() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");
			String sql = "select version() as version";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("version"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally
	}

	@Override
	public int insert(MemberVO vo) {
		System.out.println("insert()...");
		System.out.println(vo);

		int flag = 0;

		try {

			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");

			pstmt = conn.prepareStatement(MemberDB_oracle.SQL_INSERT);
			pstmt.setString(1, vo.getMember_id());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirthday());
			pstmt.setString(6, vo.getLocation());
			pstmt.setString(7, vo.getImg_name());

			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally
		return flag;
	}

	@Override
	public int update(MemberVO vo) {
		System.out.println("update()...");
		System.out.println(vo);

		int flag = 0;

		try {
			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");

			pstmt = conn.prepareStatement(MemberDB_oracle.SQL_UPDATE);

			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getLocation());
			pstmt.setString(3, vo.getImg_name());
			pstmt.setString(4, vo.getMember_id());

			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return flag;
	}

	@Override
	public int delete(MemberVO vo) {
		System.out.println("delete()...");
		System.out.println(vo);
		int flag = 0;

		try {
			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");

			pstmt = conn.prepareStatement(MemberDB_oracle.SQL_DELETE);
			pstmt.setString(1, vo.getMember_id());
			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return flag;
	}

	@Override
	public MemberVO selectOne(MemberVO vo) {
		System.out.println("selectOne()...");
		System.out.println(vo);

		MemberVO vo2 = new MemberVO();

		try {
			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");
			pstmt = conn.prepareStatement(MemberDB_oracle.SQL_SELECT_ONE);
			pstmt.setString(1, vo.getMember_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2.setMember_id(rs.getString("member_id"));
				vo2.setPw(rs.getString("pw"));
				vo2.setName(rs.getString("name"));
				vo2.setBirthday(rs.getString("birth").split(" ")[0]);
				vo2.setM_age(rs.getInt("m_age"));
				vo2.setGender(rs.getString("gender"));
				vo2.setLocation(rs.getString("location"));
				vo2.setImg_name(rs.getString("img_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return vo2;
	}

	@Override
	public MemberVO idCheck(MemberVO vo) {
		System.out.println("selectOne()...");
		System.out.println(vo);

		MemberVO vo2 = null;

		try {
			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");
			pstmt = conn.prepareStatement(MemberDB_oracle.SQL_ID_CHECK);
			pstmt.setString(1, vo.getMember_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2 = new MemberVO();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return vo2;
	}

	@Override
	public MemberVO login(MemberVO vo) {
		System.out.println("login()...");
		System.out.println(vo);

		MemberVO vo2 = null;

		try {
			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");
			pstmt = conn.prepareStatement(MemberDB_oracle.SQL_LOGIN);
			pstmt.setString(1, vo.getMember_id());
			pstmt.setString(2, vo.getPw());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2 = new MemberVO();
				vo2.setMember_id(rs.getString("member_id"));
				vo2.setName(rs.getString("name"));
				vo2.setImg_name(rs.getString("img_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return vo2;
	}

	@Override
	public int insertage(MemberVO vo) {
		System.out.println("insertage()...");
		System.out.println(vo);
		// 회원가입 생년월일 입력시 만 나이로 계산
		int flag = 0;

		try {
			conn = DriverManager.getConnection(MemberDB_oracle.URL, MemberDB_oracle.USER, MemberDB_oracle.PASSWORD);
			System.out.println("conn successed...");

			pstmt = conn.prepareStatement(MemberDB_oracle.SQL_CAL_AGE);

			pstmt.setString(1, vo.getMember_id());

			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally

		return flag;
	}

}
