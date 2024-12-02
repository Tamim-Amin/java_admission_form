package admissionform;
import java.io.Serializable;

public class Student implements Serializable {
    private String name, fatherName, motherName, contact, email, dob, age, bloodGroup, gender, department, semester, hscYear, hscGpa, sscYear, sscGpa, presentAddress, permanentAddress;

    public Student(String name, String fatherName, String motherName, String contact, String email, String dob, String age, String bloodGroup, String gender, String department, String semester, String hscYear, String hscGpa, String sscYear, String sscGpa, String presentAddress, String permanentAddress) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contact = contact;
        this.email = email;
        this.dob = dob;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.department = department;
        this.semester = semester;
        this.hscYear = hscYear;
        this.hscGpa = hscGpa;
        this.sscYear = sscYear;
        this.sscGpa = sscGpa;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
    }

    public Object[] toTableRow() {
        return new Object[] {name, fatherName, motherName, contact, email, dob, age, bloodGroup, gender, department, semester, hscYear, hscGpa, sscYear, sscGpa, presentAddress, permanentAddress};
    }
}
