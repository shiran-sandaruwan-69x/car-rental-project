package lk.ijse.service;

import lk.ijse.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    void saveBooking(BookingDTO dto);
    void updateBooking(BookingDTO dto);
    void deleteBooking(String id);
    BookingDTO searchBooking(String id);
    List<BookingDTO> getAllBooking();
    String getLastID();

    BookingDTO searchBookingId(String cusid);

}
