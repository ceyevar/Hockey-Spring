package com.example.demo.service;

import com.example.demo.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Player> findPlayersByName(String name){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM PLAYER WHERE name LIKE ? ";
		try {
			List<Player> data = jdbcTemplate.query(sql,
					new PlayerMapper(),
					new Object[]{"%" + name + "%"});
			return data;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}

	}
	public int findPlayerPositionRankNoTeam(String position){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "SELECT rownum() FROM PLAYER " +
				"WHERE position = ? AND teamid = -1" +
				"ORDER BY ovlrating DESC";
		try {
			int data = jdbcTemplate.queryForObject(sql,
					new Object[]{ position},
					Integer.class);
			return data;
		}
		catch(EmptyResultDataAccessException e){
			return -1;
		}

	}

	public void addPlayer(Player p){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO PLAYER(name,teamID, position, offskills,defskills, goalieskills) VALUES(?,?,?,?,?,?)";
		try {
			jdbcTemplate.update(sql,
					new Object[]{
						p.getName(), p.getTeamID(), p.getPosition(), p.getOffskills(), p.getDefskills(), p.getGoalieskills()});
		}
		catch(Exception e){
			System.out.println("AddPlayer :" + e);
		}
	}
	public Player findPlayerByID(int id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM PLAYER WHERE id = ?";
		try {
			Player data = jdbcTemplate.queryForObject(sql,
					new PlayerMapper(),
					new Object[]{id});
			return data;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}

	}
	public List<Player> findPlayersByTeam(int id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM PLAYER WHERE teamID = ?";
		try {
			List<Player> data = jdbcTemplate.query(sql,
					new PlayerMapper(),
					new Object[]{id});
			return data;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}

	}

	private static final class PlayerMapper implements RowMapper<Player> {

		public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
			Player player = new Player();
			player.setName(rs.getString("name"));
			player.setId(rs.getInt("id"));
			player.setOffskills(rs.getInt("offskills"));
			player.setDefskills(rs.getInt("defskills"));
			player.setTeamID(rs.getInt("teamid"));
			player.setPosition(rs.getString("position"));
			return player;
		}
	}
}
