import java.util.Enumeration;

public abstract class Statement {

    public abstract String getResultHeader(Customer aCustomer);
    public abstract String getResultBody(Rental aRental);
    public abstract String getResultfooter(Customer aCustomer);

    public String value(Customer aCustomer) {
        Enumeration rentals = aCustomer.getRentals();
        String result = getResultHeader(aCustomer);
        while (rentals.hasMoreElements()) {
           Rental each = (Rental) rentals.nextElement();
           //show figures for each rental
           result +=  getResultBody(each);
        }
        //add footer lines
        result += getResultfooter(aCustomer); 
        return result;
     }
}
