
public class Course{
    private String CourseName;
    private String CourseId;
    private int TextStock;
    public Course(){
        this.CourseName = "";
        this.CourseId = "";
        this.TextStock = 0;
    }
    public Course(String CourseNm, String CourseIden, int TextStocks){
        this.CourseName = CourseNm;
        this.CourseId = CourseIden;
        this.TextStock = TextStocks;
    }
    public String getCourseName(){
        return this.CourseName;
    }
    public String getCourseID(){
        return this.CourseId;
    }
    public int getTextStock(){
        return this.TextStock;
    }
}
