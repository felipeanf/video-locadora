import java.util.Enumeration;

public class HtmlStatement extends Statement {

    public String getResultHeader(Customer aCustomer){        
        String result = "<H1>Rentals for <EM>" + aCustomer.getName() +
        "</EM></H1><P>\n";
        return result; 
    }

    public String getResultBody(Rental aRental){        
        String result = aRental.getMovie().getTitle()+ ": " +
        String.valueOf(aRental.getCharge()) + "<BR>\n";
        return result; 
    }

    public String getResultfooter(Customer aCustomer){        
        String result = "<P>You owe <EM>" +
        String.valueOf(aCustomer.getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" + 
        String.valueOf(aCustomer.getTotalFrequentRenterPoints()) +
        "</EM> frequent renter points<P>";
        return result; 
    }

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
