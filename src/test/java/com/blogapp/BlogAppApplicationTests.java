package com.blogapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Slf4j
class BlogAppApplicationTests {

	@Autowired
	DataSource datasource;

	@Test
	void applicationCanConnectToLocalDatabaseTest(){
		assertThat(datasource).isNotNull();
		log.info("Datasource object is created");

		try(Connection connection = datasource.getConnection()){
			assertThat(datasource).isNotNull();
			assertThat(connection.getCatalog()).isEqualTo("blogdb");
			log.info("Connection --> {}", connection.getCatalog());
		} catch (SQLException throwables) {
			log.info("Exception occurred while connecting to the database -->{}", throwables.getMessage());
		}
	}

}
