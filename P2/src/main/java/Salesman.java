//Bruna de Paula Silva
//Leticia Vitoria Rodrigues Rosa

public class Salesman {
    protected int id;
    protected String name;
    protected String city;
    protected int commission;

    public Salesman() {
    }

    public Salesman(int id) {
        this.id = id;
    }

    public Salesman(int id, String name, String city, int commission) {
        this(name, city, commission);
        this.id = id;
    }

    public Salesman(String name, String city, int commission) {
        this.name = name;
        this.city = city;
        this.commission = commission;
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

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }
}
