import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    try {
        Class.forName("com.mysql.jdbc.Driver")
        print("驱动加载成功")
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
        println("驱动加载失败")
    }

    try {
        val conn = DriverManager.getConnection(
            "JDBC:MYSQL://localhost:3306/njuitms?characterEncoding=utf8",
            "root",
            "964538"
        )
        print("获取连接成功")
    } catch (e: SQLException) {
        e.printStackTrace()
        print("获取连接失败")
    }
}