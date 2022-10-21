package dao

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

// 操作数组层的父类
open class BaseDao {
    private val jdbcDriver = "com.mysql.jdbc.Driver"
    private val url = "JDBC:MYSQL://localhost:3306/njuitms?characterEncoding=utf8"
    private val user = "root"
    private val pwd = "964538"

    init {
        try {
            Class.forName(jdbcDriver)
            println("驱动加载成功")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            println("驱动加载失败")
        }
    }

    fun getConnection(): Connection? {
        var conn: Connection? = null

        try {
            conn = DriverManager.getConnection(url, user, pwd)
            println("获取连接成功")
        } catch (e: SQLException) {
            e.printStackTrace()
            println("获取连接失败")
        }
        return conn
    }

    fun close(rs: ResultSet?, ps: PreparedStatement?, conn: Connection?) {
        if (rs != null) {
            try {
                rs.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        if (ps != null) {
            try {
                ps.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        if (conn != null) {
            try {
                conn.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}