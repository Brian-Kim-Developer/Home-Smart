/*
package com.project.project6.member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

public class MemberDAOJdbc implements MemberDAO {

	private JdbcTemplate jdbcTemplate;
	private RowMapper<MemberDTOJdbc> memRowMapper =
			new RowMapper<MemberDTOJdbc>() {
				@Override
				public MemberDTOJdbc mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					MemberDTOJdbc memberDTO = new MemberDTOJdbc(rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					memberDTO.setId(rs.getLong("ID"));
					return memberDTO;
				}
			};

	public MemberDAOJdbc(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public MemberDTOJdbc getMemberByEmail(String email) {
		List<MemberDTOJdbc> results = jdbcTemplate.query(
				"select * from MEMBER  where EMAIL = ?",
				memRowMapper, email);
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public void insert(MemberDTOJdbc memberDTO) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
								"values (?, ?, ?, ?)",
						new String[] { "ID" });
				// 인덱스 파라미터 값 설정
				pstmt.setString(1, memberDTO.getEmail());
				pstmt.setString(2, memberDTO.getPassword());
				pstmt.setString(3, memberDTO.getName());
				pstmt.setTimestamp(4,
						Timestamp.valueOf(memberDTO.getRegisterDateTime()));
				// 생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		memberDTO.setId(keyValue.longValue());
	}

	@Override
	public void update(MemberDTOJdbc memberDTO) {
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
				memberDTO.getName(), memberDTO.getPassword(), memberDTO.getEmail());
	}

	@Override
	public List<MemberDTOJdbc> selectAll() {
		List<MemberDTOJdbc> results = jdbcTemplate.query("select * from MEMBER",
				memRowMapper);
		return results;
	}

	@Override
	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class);
		return count;
	}

	@Override
	public List<MemberDTOJdbc> getMembersByRegdate(LocalDateTime from, LocalDateTime to) {
		List<MemberDTOJdbc> results = jdbcTemplate.query(
				"select * from MEMBER where REGDATE between ? and ? " +
						"order by REGDATE desc",
				memRowMapper,
				from, to);
		return results;
	}

	@Override
	public MemberDTOJdbc getMemberById(Long memId) {
		List<MemberDTOJdbc> results = jdbcTemplate.query(
				"select * from MEMBER where ID = ?",
				memRowMapper, memId);

		return results.isEmpty() ? null : results.get(0);
	}

}
*/
