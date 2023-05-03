package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Booking {
    @Id
    private String bookingID;
    private String date;
    private String pickdate;
    private String status;
    private String note;
    private String returnDate;


    @ManyToOne
    @JoinColumn(name = "cusID", referencedColumnName = "customerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "carid", referencedColumnName = "carID", nullable = false)
    private Car  car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driverid", referencedColumnName = "driverID", nullable = false)
    private Driver driver;





}
