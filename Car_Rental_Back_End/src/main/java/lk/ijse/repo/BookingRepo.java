package lk.ijse.repo;

import lk.ijse.dto.BookingDTO;
import lk.ijse.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepo extends JpaRepository<Booking,String> {
    @Query(value = "SELECT bookingID FROM booking ORDER BY bookingID DESC LIMIT 1", nativeQuery = true)
    String getLastID();


    @Query(value = "SELECT * FROM booking WHERE bookingID=:bookingID",nativeQuery = true)
    BookingDTO cusBook(@Param("bookingID") String bookingID);

}
