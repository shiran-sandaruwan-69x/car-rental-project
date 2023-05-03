package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
    private String bookingID;
    private String date;
    private String pickdate;
    private String status;
    private String note;
    private String  returnDate;
    private CustomerDTO customerDto;
    private CarDTO carDto;
    private DriverDTO driverDto;
}
