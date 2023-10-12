package faker;

import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataGenerator {
    private final Faker faker;
    private final SimpleDateFormat dateFormat;
    private String checkinDate;

    public DataGenerator() {
        this.faker = new Faker();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Date format for check-in and check-out dates
    }

    public String generateFullName() {
        return faker.name().fullName();
    }

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    public String generateAddress() {
        return faker.address().fullAddress();
    }

    public String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public String generateTotalPrice() {
        return String.valueOf(faker.number().randomDouble(2, 50, 500));
    }


    public String generateCheckinDate() {
        Date date = faker.date().between(new Date(), faker.date().future(30, TimeUnit.DAYS));
        checkinDate = dateFormat.format(date);
        return checkinDate;
    }

    public String generateCheckoutDate() {
        try {
            Date checkin = dateFormat.parse(checkinDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkin);
            calendar.add(Calendar.DAY_OF_MONTH, 5); // Add 5 days to the check-in date
            Date checkout = calendar.getTime();
            return dateFormat.format(checkout);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date"; // Handle the exception as needed
        }

    }

    public String generateExtraNeeds() {
        return faker.lorem().fixedString(5);
    }

//    public static void main(String[] args) {
//        DataGenerator dataGenerator = new DataGenerator();
//
//        System.out.println("Full Name: " + dataGenerator.generateFullName());
//        System.out.println("First Name: " + dataGenerator.generateFirstName());
//        System.out.println("Last Name: " + dataGenerator.generateLastName());
//        System.out.println("Email: " + dataGenerator.generateEmail());
//        System.out.println("Address: " + dataGenerator.generateAddress());
//        System.out.println("Phone Number: " + dataGenerator.generatePhoneNumber());
//        System.out.println("Total Price: " + dataGenerator.generateTotalPrice());
//        System.out.println("Check-in Date: " + dataGenerator.generateCheckinDate());
//        System.out.println("Checkout Date: " + dataGenerator.generateCheckoutDate());
//        System.out.println("Extra Needs: " + dataGenerator.generateExtraNeeds());
//    }
}
