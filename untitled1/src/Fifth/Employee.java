package Fifth;

/**
 * ManageSys3 里用 new Employee() 往 IMove 数组里放元素，但原文件整个类都被注释掉了，
 * 编译时报“找不到符号 Employee”。这里按上课代码（demo2025_3_25/Employee.java）恢复。
 */
public class Employee implements IMove {
    private String name;
    private int age;

    public Employee() {
    }

    public void method1() {
    }

    public void method2() {
    }

    @Override
    public void move(int hour) {
        System.out.println("employee:" + hour * 10);
    }
}
