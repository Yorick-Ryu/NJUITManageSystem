import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object JDBC {
    private const val JdbcDriver = "com.mysql.jdbc.Driver"
    private const val url = "JDBC:MYSQL://localhost:3306/njuitms?characterEncoding=utf8"
    private const val user = "root"
    private const val pwd = "964538"
    val connection: Connection?
        get() = getConn()

    private fun getConn(): Connection? {
        var conn: Connection? = null
        try {
            Class.forName(JdbcDriver)
            print("驱动加载成功")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            println("驱动加载失败")
        }

        try {
            conn = DriverManager.getConnection(url, user, pwd)
            print("获取连接成功")
        } catch (e: SQLException) {
            e.printStackTrace()
            print("获取连接失败")
        }
        return conn
    }
}

