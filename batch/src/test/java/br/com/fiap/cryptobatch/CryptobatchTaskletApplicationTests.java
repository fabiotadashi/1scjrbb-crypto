package br.com.fiap.cryptobatch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CryptobatchTaskletApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		ResultSet resultSet = dataSource.getConnection()
				.prepareStatement("select count(*) from TB_CRYPTOS")
				.executeQuery();
		resultSet.next();
		await().atMost(5, TimeUnit.SECONDS)
				.until(() -> resultSet.getInt(1) == 3);
		assertEquals( 3, resultSet.getInt(1));
	}

}
