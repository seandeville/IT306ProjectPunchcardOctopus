public class Orders{
    private Student studentOrder;
    private Course courseOrder;
    public Orders(){
        this.studentOrder = null;
        this.courseOrder = null;
    }
    public Orders (Student stud_ord, Course cours_ord){
        this.studentOrder = stud_ord;
        this.courseOrder = cours_ord;
    }
    public Student getStudent(){
        return this.studentOrder;
    }
    public Course getCourse(){
    return this.courseOrder;
    }
}
