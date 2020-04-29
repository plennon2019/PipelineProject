package employee.com;

public abstract class Manager extends Employee {

    private double salary;

    public Manager(String employeeId, String name, String address, String dob, String ppsNo, double salary) {
        super(employeeId, name, address, dob, ppsNo);
        setSalary(salary);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
