package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    private String paymentID;
    private String cusId;
    private String carId;
    private String date;
    private String returnDate;
    private double noOfKm;
    private double amount;

}
