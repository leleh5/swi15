//Bruna de Paula Silva
//Leticia Vitoria Rodrigues Rosa

public class Customer {
    protected int id;
    protected String name;
    protected String city;
    protected int grade;
    protected int salesman_id;

    public Customer() {
    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer(int id, String name, String city, int grade, int salesman_id) {
        this(name, city, grade, salesman_id);
        this.id = id;
    }

    public Customer(String name, String city, int grade, int salesman_id) {
        this.name = name;
        this.city = city;
        this.grade = grade;
        this.salesman_id = salesman_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_id(int salesman_id) {
        this.salesman_id = salesman_id;
    }
}
