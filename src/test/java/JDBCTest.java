import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class JDBCTest {
    @BeforeAll
    public static void setup() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("JDBC 드라이버 연결이 된다")
    public void testConnection() {
        String url = "jdbc:mysqlL//localhost:3306/tetzdb";
        try (Connection con = DriverManager.getConnection(url, "suminiee", "1234")) {
            log.info("con={}", con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
