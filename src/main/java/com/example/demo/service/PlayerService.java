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
	public Player findPlayerByID(int id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM PLAYER WHERE id = ? ";
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
	private static final class PlayerMapper implements RowMapper<Player> {

		public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
			Player player = new Player();
			player.setName(rs.getString("name"));
			player.setId(rs.getInt("id"));
			player.setOffskills(rs.getInt("offskills"));
			player.setDefskills(rs.getInt("defskills"));
			return player;
		}
	}
}
