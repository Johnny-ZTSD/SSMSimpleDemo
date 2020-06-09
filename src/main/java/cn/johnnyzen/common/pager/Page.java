package cn.johnnyzen.common.pager;

/**
 * @author: 千千寰宇
 * @date: 2020/6/9  10:50:30
 * @description:
 *  阅读文献
 *      + https://www.cnblogs.com/SimonHu1993/p/7791979.html
 *      + https://www.cnblogs.com/mikedeng/p/better_pagination.html
 *      + https://www.lyscms.info/blog/detail/88D932E21D0647CFB52740DB73EB2027
 *  Page的属性
 *      + 需查询数据库的属性
 *          记录总数(totalCount)、当前分页的数据集(记录集)(dataSet)、
 *      + 计算所得的属性
 *          总页数(totalPages)、偏移记录数(offset)
 *      + 预先设置的属性
 *          页面大小(pageSize=20)、当前页码(curPage=1)
 *  Page的使用流程
 *      + step-1 [前端] UI控制：【首页、尾页、页码、上一页、下一页】
 *      -------------------------------------------------------
 *      + step0 [控制层:接收参数] 接收【页面大小】、【当前页码】
 *      + step2 [Service层:初始化Page] 传入【页面大小】、【当前页码】；并计算【偏移记录数】
 *          BookService.java:
 *              Page<Book> listBooks(...,@curPage=2, @pageSize=20){
 *                  page.init(@pageSize, @curPage){
 *                      this.pageSize=DEFAULT_PAGE_ITEMS_MAX_SIZE;
 *                      this.setPageSize(@pageSize);
 *                      this.setCurPage(@curPage);
 *                      this.setOffset(@offset=(@curPage-1)*@pageSize);
 *                  }
 *                  List<Book> books = BookMapper.listBooks(@curPage, @pageSize, @offset);
 *                  ...
 *              }
 *      + step3 [DAO层:SQL查询] SQL查询
 *          BookMapper.java:
 *              List<Book> listBooks(...,@curPage=2, @pageSize=20,[@offset=20]);
 *          BookMapper.xml:(仅以 MySQL 为例)
 *              <select id='#listBooks'>
 *                  select * from tb_book limit @offset, @pageSize;
 *              </select>
 *      + step4 [Service层:封装Page其他属性] 封装SQL查询结果到【当前分页数据集】、【记录总数】
 *          BookService.java:
 *              Page<Book> listBooks(...,@curPage=2, @pageSize=20){
 *                  ...
 *                  List<Book> books = BookMapper.listBooks(@curPage, @pageSize, @offset);
 *                  page.setDataSet(books);//当前分页数据集
 *                  totalCount = BookMapper.totalCountListBooks();//单独执行统计记录总数的SQL
 *                  page.setTotalCount(totalCount);//记录总数
 *              }
 *      + step4 [Page:计算其他属性] 根据前面步骤所设属性，计算【总页数】
 *          总页数=记录总数/页面大小+记录总数%页面大小==0?0:1
 *          由总页数的公式可知，总页数的影响因子: 记录总数、页面大小
 *      + step5 [Service层:获得Page]
 */

/*import cn.johnnyzen.common.MapperUtil;
import cn.johnnyzen.common.print.Print;
import cn.johnnyzen.hiyusite.user.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;*/

import java.util.List;

public class Page<T> {
    /**
     * 记录开始数的默认值
     * 即 偏移值，从何处开始计(记录)数的默认值
     */
    private final static int DEFAULT_OFFSET = 0;

    /**
     * 每页的记录数的最大值的默认值
     */
    private final static int DEFAULT_PAGE_ITEMS_MAX_SIZE = 20;


    /**
     * 每页的记录数的最大值
     * 即 每页显示条数的最大值
     * 例如： 每页5条
     */
    private int pageSize = DEFAULT_PAGE_ITEMS_MAX_SIZE;  //

    /**
     * 记录总数
     * 即 所有页面的记录数的总和
     * 例如 : 总共员工数，数据库中总的记录数，这里有17条
     */
    private int totalCount;

    /**
     * 总页数
     * 即 页面总数
     */
    private int totalPages;

    /**
     * 当前页码
     * 例如 当前第2页
     */
    private int curPage;

    /**
     * 当前分页记录集
     */
    private List<T> dataSet;

    /**
     * 记录开始数
     * 即 偏移值，从何处开始计(记录)数
     * 默认值 0
     */
    private int offset=DEFAULT_OFFSET;

    public Page(int curPage, int pageSize,  List<T> dataSet){
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.dataSet = dataSet;
        updateTotalPages();
        updateOffset();
    }

    public Page(int curPage, int pageSize){
        this(curPage, pageSize,  null);
    }

    public Page(int curPage){
        this(curPage, DEFAULT_PAGE_ITEMS_MAX_SIZE);
    }

    public boolean isFirst(){
        return getCurPage()==1;
    }

    public boolean isLast(){
        return getCurPage()==getTotalPages();
    }

    public boolean hasNext(){  //有下一页
        return getCurPage()<getTotalPages();                //当前不是尾页，且总页数大于2
    }

    /**
     * 获得下一页的页码
     *  若当前页不是最后一页，则： 下一页码=当前页码+1；
     *  反之，则： 下一页码=当前页
     */
    public int getNext(){
        return hasNext()?getCurPage()+1:getCurPage();
    }

    public boolean hasPrevious(){  //有上一页
        return getCurPage()>1;                //当前不是尾页，且总页数大于2
    }

    /**
     * 获得上一页的页码
     *  若当前页不是第一页，则： 上一页码=当前页码-1；
     *  反之，则： 上一页码=当前页
     */
    public int getPrevious(){
        return hasPrevious()?getCurPage()-1:getCurPage();
    }

    //更新 页面总数
    private void updateTotalPages(){
        pageSize = pageSize!=0?pageSize:20;//防止分母为0
        this.totalPages = totalCount/pageSize + (totalCount%pageSize==0?0:1);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        updateTotalPages();
    }

    public int getCurPage() {
        return curPage;
    }

    public int getOffset() {
        return offset;
    }

    private void updateOffset(){
        this.offset = (curPage-1)*pageSize;
    }

    public int getTotalPages() {         //获取总页数
        updateTotalPages();
        return totalPages;
    }

    public List getDataSet() {
        return dataSet;
    }

    public void setDataSet(List dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public String toString() {
        return "Page{" +
                "\n\tpageSize=" + pageSize +
                ",\n\ttotalCount=" + totalCount +
                ",\n\ttotalPages=" + totalPages +
                ",\n\tcurPage=" + curPage +
                ",\n\tdataSet=" + dataSet +
                ",\n\toffset=" + offset +
                "\n}";
    }

/*    public static void main(String[] args) {
        Page page = new Page(3, 2);
        UserMapper userMapper = MapperUtil.getSqlSession(false).getMapper(UserMapper.class);
        page.setTotalCount(userMapper.countTotalListUsers());//唯二的setter
        page.setDataSet( userMapper.listUsers(page.getOffset(), page.getPageSize()) );//唯二的setter
        Print.print(page);
    }*/
    /*
    Page{
        pageSize=2,
        totalCount=6,
        totalPages=3,
        curPage=3,
        dataSet=[
                User{id=6, nickname='johnnyzen', email='johnnyzen@gmail.com', password='123456'},
                User{id=7, nickname='kacy', email='kacy@gmail.com', password='123456'}
            ],
        offset=4
    }
    */
}
