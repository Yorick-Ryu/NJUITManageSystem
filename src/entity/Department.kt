package entity

class Department {
    var deptId:Int = 0
    var deptName:String = ""
    var deptLoc:String = ""
    var deptMan:Int = 0
    override fun toString(): String {
        return "Department(deptId=$deptId, deptName='$deptName', deptLoc='$deptLoc', deptMan=$deptMan)"
    }

}