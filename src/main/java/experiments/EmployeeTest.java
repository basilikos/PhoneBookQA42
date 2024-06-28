package experiments;

public class EmployeeTest {

    public void testMethod() {
        Employee employee = Employee
                .builder()
                .name("Name")
                .age(55)
                .build();
        employee.setAge(45);
        employee.setName("Name1");
    }

}
