//Bruna de Paula Silva
//Leticia Vitoria Rodrigues Rosa

public class Orders {
    protected int id;
    protected int purch_amt;
    protected String date;
    protected int customer_id;
    protected int salesman_id;

    public Orders() {
    }

    public Orders(int id) {
        this.id = id;
    }

    public Orders(int id, int purch_amt, String date, int customer_id, int salesman_id) {
        this.id = id;
        this.purch_amt = purch_amt;
        this.date = date;
        this.customer_id = customer_id;
        this.salesman_id = salesman_id;
    }

    public Orders(int purch_amt, String date, int customer_id, int salesman_id) {
        this.purch_amt = purch_amt;
        this.date = date;
        this.customer_id = customer_id;
        this.salesman_id = salesman_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchAmt() {
        return purch_amt;
    }

    public void setPurchAmt(int purch_amt) {
        this.purch_amt = purch_amt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }
    public int getSalesmanId() {
        return salesman_id;
    }

    public void setSalesmanId(int salesman_id) {
        this.salesman_id = salesman_id;
    }
}

