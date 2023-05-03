package lk.ijse.service.impl;

import lk.ijse.dto.BookingDTO;
import lk.ijse.dto.CarDTO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.DriverDTO;
import lk.ijse.entity.Booking;
import lk.ijse.entity.Car;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Driver;
import lk.ijse.repo.BookingRepo;
import lk.ijse.repo.CarRepo;
import lk.ijse.repo.CustomerRepo;
import lk.ijse.repo.DriverRepo;
import lk.ijse.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveBooking(BookingDTO dto) {
        Booking booking = modelMapper.map(dto, Booking.class);
        booking.setBookingID(getLastID());

        Booking booking1 = new Booking(
                booking.getBookingID(),
                booking.getDate(),
                booking.getPickdate(),
                booking.getStatus(),
                booking.getNote(),
                booking.getReturnDate(),
                booking.getCustomer(),
                booking.getCar(),
                booking.getDriver()
        );
        bookingRepo.save(booking1);
    }

    @Override
    public void updateBooking(BookingDTO dto) {
        if(bookingRepo.existsById(dto.getBookingID())){
            bookingRepo.save(modelMapper.map(dto, Booking.class));
        }else{
            throw new RuntimeException("No such Booking for update..!");
        }
    }

    @Override
    public void deleteBooking(String id) {
        if(bookingRepo.existsById(id)){
            bookingRepo.deleteById(id);
        }else{
            throw new RuntimeException("No such Booking for update..!");
        }
    }

    @Override
    public BookingDTO searchBooking(String id) {
        Optional<Booking> booking = bookingRepo.findById(id);
        if(booking.isPresent()){
            return modelMapper.map(booking, BookingDTO.class);
        }else{
            throw new RuntimeException("No Booking for id: " + id);
        }
    }

    @Override
    public List<BookingDTO> getAllBooking() {
        List<Booking> bookings = bookingRepo.findAll();
        List<BookingDTO> bookingDtos = new ArrayList<>();

        for (Booking b : bookings) {
            Customer customer = b.getCustomer();
            Car car = b.getCar();
            Driver driver = b.getDriver();

            CustomerDTO customer1 = modelMapper.map(customer, CustomerDTO.class);
            CarDTO car1 = modelMapper.map(car, CarDTO.class);
            DriverDTO driver1 = modelMapper.map(driver, DriverDTO.class);

            BookingDTO dto = modelMapper.map(b, BookingDTO.class);
            dto.setCustomerDto(customer1);
            dto.setCarDto(car1);
            dto.setDriverDto(driver1);

            bookingDtos.add(dto);

        }
        return bookingDtos;
    }



    @Override
    public String getLastID() {
        String lastID = bookingRepo.getLastID();
        if (lastID != null) {
            String[] split = lastID.split("B");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id < 10) return "B00" + id;
            else if (id < 100) return "B0" + id;
            else return "B" + id;
        }else{
            return "B001";
        }
    }

    @Override
    public BookingDTO searchBookingId(String cusid) {
       return bookingRepo.cusBook(cusid);

    }


}
