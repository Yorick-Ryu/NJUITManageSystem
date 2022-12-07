package service

import entity.Department
import org.junit.jupiter.api.Test
import service.impl.DeptServiceImpl

class TestDeptService {

    private val dept = Department()
    private val deptService: DeptService = DeptServiceImpl()

    @Test
    fun testAddDept() {
        dept.apply {
            deptId = 5
            deptName = "保卫部"
            deptLoc = "行政楼117"
            deptMan = 2
        }
         println(deptService.addDept(dept))
    }
    @Test
    fun  testUpdateDept(){
        dept.apply {
            deptId = 5
            deptName = "讲课部"
            deptLoc = "教育楼117"
            deptMan = 3
        }
        println(deptService.updateDept(dept))
    }
    @Test
    fun testDelDept(){
        println(deptService.delDept(8))
    }

    @Test
    fun testQueryAllDept(){
        val departments = deptService.queryAllDept()
        for (dept in departments){
            println(dept)
        }
    }
}