package comp.Midterm;
public class Student{
    private String name;
    private float gpa;
    private int bannerID;
    public Student(String initName, float initGPA, int initBannerID){
        this.name = initName;
        this.gpa = initGPA;
        this.bannerID = initBannerID;
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName; // sometimes names legally change
    }
    public float getGpa(){
        return gpa;
    }
    public void setGpa(float newGPA){
        gpa = newGPA;
    }
    public int getBannerID(){
        return bannerID;
    }
}
