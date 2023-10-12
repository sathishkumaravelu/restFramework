package test;

import basePackage.RAWrapper;
import faker.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.createBookingRequest;

public class tc_01_CreateBooking extends RAWrapper {

    public DataGenerator data;
    public String bookingid;

    @Test
    public void tc_createBooking() throws Exception {
        data = new DataGenerator();
        Response aTrue = createBooking(data.generateFirstName(), data.generateLastName(), data.generateTotalPrice(), "true", data.generateCheckinDate(), data.generateCheckoutDate(), data.generateExtraNeeds());
        aTrue.then().statusCode(200);
        bookingid = aTrue.jsonPath().get("bookingid").toString();

    }

    @Test(dependsOnMethods = {"tc_createBooking"})
    public void tc_getBooking() throws Exception {
        Response booking = getBooking(bookingid);
        booking.prettyPrint();
        booking.then().statusCode(200);
    }

    @Test(dependsOnMethods = {"tc_createBooking"})
    public void tc_updateBooking() throws Exception {
        createBookingRequest bookingPojo = getBookingPojo(bookingid);
        bookingPojo.setLastname(data.generateLastName());
        Response response = updateBookingPojoBody(bookingPojo, bookingid);
        response.prettyPrint();
    }


}
