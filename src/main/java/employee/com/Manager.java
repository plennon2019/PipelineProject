package employee.com;

public abstract class Manager extends Employee {

    private double salary;

<<<<<<< HEAD


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
=======
    Manager(String employeeId, String name, String address, String DOB, String ppsNo, double salary) {
        super(employeeId, name, address, DOB, ppsNo);
        this.salary = salary;
>>>>>>> c9ba444d8cc2d6b3c7f180f89320f88c3a22d794
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
