import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;

public class UntityTests{
    
    /* Testing Price methods*/ 

    int days_rented = 5;

    @Test // test number 1;
    public void testRegularPrice(){
        RegularPrice price = new RegularPrice();
        double total_charge = price.getCharge(days_rented);
        int price_code = price.getPriceCode();
        assertEquals(0, price_code);
        assertEquals(6.5, total_charge);
    }
    @Test // test number 2;
    public void testGetChilddrensPrice(){
        ChildrensPrice price = new ChildrensPrice();
        double total_charge = price.getCharge(days_rented);
        int price_code = price.getPriceCode();
        assertEquals(2, price_code);
        assertEquals(4.5, total_charge);
    }
    @Test // test number 3;
    public void testNewReleasePrice(){
        NewReleasePrice price = new NewReleasePrice();
        double total_charge = price.getCharge(days_rented);
        int frequent_renter_points = price.getFrequentRenterPoints(days_rented);
        int price_code = price.getPriceCode();
        assertEquals(1, price_code);
        assertEquals(15, total_charge);
        assertEquals(2, frequent_renter_points);
    }

    @Test // test number 4;
    public void testSetPriceCode(){
        Movie movie_regular = new Movie("Titanic", 0);
        assertThat(movie_regular._price, is(instanceOf(RegularPrice.class)));

        Movie movie_children = new Movie("Frozen", 2);
        assertThat(movie_children._price, is(instanceOf(ChildrensPrice.class)));

        Movie movie_newRelease = new Movie("Avengers", 1);
        assertThat(movie_newRelease._price, is(instanceOf(NewReleasePrice.class)));

        assertThrows(IllegalArgumentException.class, new Executable() {
             
            @Override
            public void execute() throws Throwable {
                new Movie("2012", 5);
            }
        });   
    }

     /* Testing Rental methods*/ 

     @Test // teste number 5;
     public void testgetRentalInfos(){
         Rental new_rental = new Rental(new Movie("Frozen", 2), days_rented);

         int daysRented = new_rental.getDaysRented();
         assertEquals(daysRented, daysRented);

         double charge = new_rental.getCharge();
         assertEquals(4.5, charge);

         int frequentRentPoins = new_rental.getFrequentRenterPoints();
         assertEquals(1, frequentRentPoins);
     }


     /* Testing Customer methods*/ 
     
    @Test // teste number 6;
    public void testGetTotalCharge(){
        Customer customer_joao = new Customer("João");
        customer_joao.addRental(new Rental(new Movie("Frozen", 2), days_rented));
        customer_joao.addRental(new Rental(new Movie("Titanic", 0), days_rented));
        customer_joao.addRental(new Rental(new Movie("Avengers", 1), days_rented));

        int total_frequentRenterPoints = customer_joao.getTotalFrequentRenterPoints();
        assertEquals(4, total_frequentRenterPoints);
 
    }

    @Test // teste number 7;
    public void testGetTextStatement(){
        Customer customer_joao = new Customer("João");
        customer_joao.addRental(new Rental(new Movie("Frozen", 2), days_rented));
        customer_joao.addRental(new Rental(new Movie("Titanic", 0), days_rented));
        customer_joao.addRental(new Rental(new Movie("Avengers", 1), days_rented));

        String result = customer_joao.statement();
        assertEquals("Rental Record for João\n\tFrozen\t4.5\n\tTitanic\t6.5\n\tAvengers\t15.0\nAmount owed is 26.0\nYou earned 4 frequent renter points", result);
    }

    @Test // teste number 8;
    public void testGetHTMLStatement(){
        Customer customer_joao = new Customer("João");
        customer_joao.addRental(new Rental(new Movie("Frozen", 2), days_rented));
        customer_joao.addRental(new Rental(new Movie("Titanic", 0), days_rented));
        customer_joao.addRental(new Rental(new Movie("Avengers", 1), days_rented));
        String result = customer_joao.htmlStatement();
        assertEquals("<H1>Rentals for <EM>João</EM></H1><P>\nFrozen: 4.5<BR>\nTitanic: 6.5<BR>\nAvengers: 15.0<BR>\n<P>You owe <EM>26.0</EM><P>\nOn this rental you earned <EM>4</EM> frequent renter points<P>", result);
    }

    
}
