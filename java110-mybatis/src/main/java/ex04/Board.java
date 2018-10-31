package ex04;

import java.sql.Date;

public class Board {
    protected int no;
    protected String title;
    protected String content;
    protected int viewCount;
    protected Date createDate;
    protected int mamberNo;
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getViewCount() {
        return viewCount;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public int getMamberNo() {
        return mamberNo;
    }
    public void setMamberNo(int mamberNo) {
        this.mamberNo = mamberNo;
    }

}
