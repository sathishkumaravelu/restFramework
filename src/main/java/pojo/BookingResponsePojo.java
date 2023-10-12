package pojo;

import lombok.Data;

@Data
public class BookingResponsePojo {
    private String bookingid;
    private createBookingRequest booking;
    private String additionalneeds;
}
