import java.util.Comparator;

public class CourseComparator implements Comparator<Course> {
    @Override
    public int compare(Course c1, Course c2)
    {
        if (c1.getValue() == c2.getValue()) {
            return 0;
        }
        else if (c1.getValue() < c2.getValue()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
