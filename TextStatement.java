import java.util.Enumeration;

public class TextStatement extends Statement {

    public String getResultHeader(Customer aCustomer){        
        String result = "Rental Record for " + aCustomer.getName() +
        "\n";
        return result; 
    }

    public String getResultBody(Rental aRental){        
        String result = "\t" + aRental.getMovie().getTitle()+ "\t" +
        String.valueOf(aRental.getCharge()) + "\n";
        return result; 
    }

    public String getResultfooter(Customer aCustomer){        
        String result = "Amount owed is " +
        String.valueOf(aCustomer.getTotalCharge()) + "\n";
        result += "You earned " +
        String.valueOf(aCustomer.getTotalFrequentRenterPoints()) +
        " frequent renter points";
        return result; 
    }

    public String value(Customer aCustomer) {
        Enumeration rentals = aCustomer.getRentals();
        String result = getResultHeader(aCustomer);
        while (rentals.hasMoreElements()) {
           Rental each = (Rental) rentals.nextElement();
           //show figures for this rental
           result +=  getResultBody(each);
        }
        //add footer lines
        result += result += getResultfooter(aCustomer); 
        return result;
     }

    }
