package util

class SplitPage {
    var pageRows = 5 //每页显示记录数,默认10条,可以在页面设置
        set(pageRows) {
            if (pageRows == 0) throw ArithmeticException()  //如果pageRows被设置为零,应当抛出异常.
            field = pageRows  //修改每页显示记录数,将会直接影响总页面数,所以要同时修改
            totalPages =
                if (totalRows % field == 0) totalRows / field else totalRows / field + 1
        }

    private var totalRows = 0 //总的记录数,这个参数由DAO对象提供
    var currentPage = 1 //当前显示的页面编号,默认第一页
    private var firstPage = 1 //首页位置,默认第一页

    //不应该提供方法设置总页面数,它是计算得到的
    //但应当提供获取总页面数的方法.
    private var totalPages = 1 //总的页面数量,默认就一页

    //设置分页对象的总记录属性后,就应该根据每页面显示记录数,计算得到总的页面数
    fun setTotalRows(totalRows: Int) {
        this.totalRows = totalRows
        //计算总的页面数(或最后的页面号),两个整数相除如果刚好除尽,值就是相除后的商,否则如果有余数,商应当还加1.
        totalPages =
            if (this.totalRows % pageRows == 0) this.totalRows / pageRows else this.totalRows / pageRows + 1
    }

    //根据请求页面的标识参数,重新计算当前要显示的页面
    //核心方法,实现分页显示功能.
    fun confirmPage(flag: String?): Int {
        val newPage: Int = if (flag != null) { //flag只可能是下面值之一
            if (flag == FIRSTPAGE) {
                1
            } else if (flag == LASTPAGE) {
                totalPages
            } else if (flag == NEXTPAGE) {
                //页面总数和当前页面编号相等吗,如果是那么页面编号不往后走,否则页面编号加一
                if (totalPages == currentPage) currentPage else currentPage + 1
            } else if (flag == PREVIOUSEPAGE) {
                //第一个页面和当前页面相等吗,如果是那么页面编号不往前走,否则页面编号减一
                if (firstPage == currentPage) currentPage else currentPage - 1
            } else { //否则是一个数字字符串
                val tpage = flag.trim { it <= ' ' }.toInt()
                tpage
            }
        } else { //如果请求标识参数为空,那么当前页码不变
            currentPage
        }
        //在返回前设置当前页面
        currentPage = newPage
        return newPage
    }

    companion object {
        //分页请求时,请求标识参数
        const val FIRSTPAGE = "first" //请求第一页
        const val PREVIOUSEPAGE = "previous" //请求上一页
        const val NEXTPAGE = "next" //请求下一页
        const val LASTPAGE = "last" //请求最后一页
    }
}