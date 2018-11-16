package com.example.demo.service;

import com.example.demo.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TeamService {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Team findTeamById(int id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM TEAM WHERE id = ?";
		try {
			Team data = jdbcTemplate.queryForObject(sql,
					new TeamService.TeamMapper(),
					new Object[]{id});
			return data;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	public void updateTeamRow(Team team){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		if(team == null){
			return;
		}
		String sql = "UPDATE TEAM SET " +
				"C1 = ? , C2 = ?, C3 = ?, C4 = ?,"+
				"LW1 = ?, LW2 = ?, LW3 = ?, LW4 = ?, " +
				"RW1 = ?, RW2 = ?, RW3 = ?, RW4 = ?, " +
				"LD1 = ?, LD2 = ?, LD3 = ?, " +
				"RD1 = ?, RD2 = ?, RD3 = ?, " +
				"G1 = ?, G2 = ? " +
				"WHERE id = ?";
		try {
			jdbcTemplate.update(sql,
					new Object[]{
						team.getC1(), team.getC2(), team.getC3(), team.getC4(),
						team.getLW1(), team.getLW2(), team.getLW3(), team.getLW4(),
						team.getRW1(), team.getRW2(), team.getRW3(), team.getRW4(),
							team.getLD1(), team.getLD2(), team.getLD3(),
							team.getRD1(), team.getRD2(), team.getRD3(),
							team.getG1(), team.getG2(), team.getId()
					});
		}
		catch(EmptyResultDataAccessException e){
			System.out.println("oops");
		}
	}
	private static final class TeamMapper implements RowMapper<Team> {

		public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
			Team team = new Team();

			team.setId(rs.getInt("id"));

			team.setC1(rs.getInt("C1"));
			team.setsC1(rs.getString("sC1"));
			team.setC2(rs.getInt("C2"));
			team.setsC2(rs.getString("sC2"));
			team.setC3(rs.getInt("C3"));
			team.setsC3(rs.getString("sC3"));
			team.setC4(rs.getInt("C4"));
			team.setsC4(rs.getString("sC4"));

			team.setLW1(rs.getInt("LW1"));
			team.setsLW1(rs.getString("sLW1"));
			team.setLW2(rs.getInt("LW2"));
			team.setsLW2(rs.getString("sLW2"));
			team.setLW3(rs.getInt("LW3"));
			team.setsLW3(rs.getString("sLW3"));
			team.setLW4(rs.getInt("LW4"));
			team.setsLW4(rs.getString("sLW4"));

			team.setRW1(rs.getInt("RW1"));
			team.setsRW1(rs.getString("sRW1"));
			team.setRW2(rs.getInt("RW2"));
			team.setsRW2(rs.getString("sRW2"));
			team.setRW3(rs.getInt("RW3"));
			team.setsRW3(rs.getString("sRW3"));
			team.setRW4(rs.getInt("RW4"));
			team.setsRW4(rs.getString("sRW4"));

			team.setLD1(rs.getInt("LD1"));
			team.setsLD1(rs.getString("sLD1"));
			team.setRD1(rs.getInt("RD1"));
			team.setsRD1(rs.getString("sRD1"));

			team.setLD2(rs.getInt("LD2"));
			team.setsLD2(rs.getString("sLD2"));
			team.setRD2(rs.getInt("RD2"));
			team.setsRD2(rs.getString("sRD2"));

			team.setLD3(rs.getInt("LD3"));
			team.setsLD3(rs.getString("sLD3"));
			team.setRD3(rs.getInt("RD3"));
			team.setsRD3(rs.getString("sRD3"));

			team.setG1(rs.getInt("G1"));
			team.setsG1(rs.getString("sG1"));
			team.setG2(rs.getInt("G2"));
			team.setsG2(rs.getString("sG2"));

			team.setTeamName(rs.getString("team_name"));

			return team;
		}
	}
}
