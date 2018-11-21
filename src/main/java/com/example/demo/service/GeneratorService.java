package com.example.demo.service;

import com.example.demo.model.FirstName;
import com.example.demo.model.LastName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorService {
	@Autowired
	private DataSource dataSource;

	private static final String nationalities[] = {"american", "czech", "finnish", "russian", "slovak", "swedish", "swiss"};

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int countRowsInFirstLast(){

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT count(*) FROM FIRST_NAME";
		String sql2 = "SELECT count(*) FROM LAST_NAME";

		int f_count= 0;
		int l_count = 0;
		try {
			f_count = jdbcTemplate.queryForObject(sql, Integer.class);
			l_count = jdbcTemplate.queryForObject(sql2, Integer.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return f_count + l_count;
	}
	public void insertAllNames(){
		insertAllFirstNames();
		insertAllLastNames();
	}
	private ArrayList<LastName> getAllLastNames(String ethnicity){

		ArrayList<LastName> allLastNames = new ArrayList<>();
		try {

			BufferedReader bufferreader = new BufferedReader(new FileReader("src/main/resources/static/namegen/" + ethnicity + "_l.txt"));


			String line;
			while ((line = bufferreader.readLine()) != null) {
				/**
				 Your implementation
				 **/
				LastName f = new LastName();
				f.setEthnicity(ethnicity);
				f.setName(line);

				allLastNames.add(f);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return allLastNames;
	}
	private ArrayList<FirstName> getAllFirstNames(String ethnicity){

		ArrayList<FirstName> allFirstNames = new ArrayList<>();
		try {

			BufferedReader bufferreader= new BufferedReader(new FileReader("src/main/resources/static/namegen/" + ethnicity + "_f.txt"));


			String line;
			while ((line = bufferreader.readLine()) != null) {
				/**
				 Your implementation
				 **/
				FirstName f = new FirstName();
				f.setEthnicity(ethnicity);
				f.setName(line);

				allFirstNames.add(f);

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return allFirstNames;
	}
	private void insertAllLastNames(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO Last_Name(name, ethnicity) VALUES(?,?)";

		for(String nationality : nationalities){
			ArrayList<LastName> allNames = getAllLastNames(nationality);
			for(LastName lastName : allNames){
				try {
					jdbcTemplate.update(sql,
							lastName.getName(), lastName.getEthnicity());
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	public List<FirstName> findFirstNameByEthnicty(String ethnicity){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM FIRST_NAME WHERE ethnicity = ?";
		try {
			List<FirstName> data = jdbcTemplate.query(sql,
					new FirstNameMapper(),
					new Object[]{ethnicity});
			return data;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}

	}
	public List<LastName> findLastNameByEthnicty(String ethnicity){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM LAST_NAME WHERE ethnicity = ?";
		try {
			List<LastName> data = jdbcTemplate.query(sql,
					new LastNameMapper(),
					new Object[]{ethnicity});
			return data;
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}

	}
	private void insertAllFirstNames(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO First_Name(name, ethnicity) VALUES(?,?)";

		for(String nationality : nationalities){
			ArrayList<FirstName> allNames = getAllFirstNames(nationality);
			for(FirstName firstName : allNames){
				try {
					jdbcTemplate.update(sql,
							firstName.getName(), firstName.getEthnicity());
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	private static final class FirstNameMapper implements RowMapper<FirstName> {

		public FirstName mapRow(ResultSet rs, int rowNum) throws SQLException {
			FirstName name = new FirstName();
			name.setName(rs.getString("name"));
			name.setEthnicity(rs.getString("ethnicity"));
			return name;
		}
	}

	private static final class LastNameMapper implements RowMapper<LastName> {

		public LastName mapRow(ResultSet rs, int rowNum) throws SQLException {
			LastName name = new LastName();
			name.setName(rs.getString("name"));
			name.setEthnicity(rs.getString("ethnicity"));
			return name;
		}
	}
}
