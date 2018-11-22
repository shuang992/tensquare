package entity;

import java.util.List;

/**
 * @param <T>
 * @description: 查询分页结果返回对象
 */
public class PageResult<T> {
    private Long total;//总记录数
    private List<T> rows;//当前页记录

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
