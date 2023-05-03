package lk.ijse.Controller;

import lk.ijse.dto.DriverDTO;
import lk.ijse.service.DriverService;
import lk.ijse.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/driver")
public class DriverController {
    @Autowired
    DriverService driverService;


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addDriver(@RequestBody DriverDTO dto){
//        dto.setPassword(hashPassword(dto.getPassword()));
        driverService.saveDriver(dto);
        StandardResponse response = new StandardResponse(200, "Success", null);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateDriver(@RequestBody DriverDTO dto){
        driverService.updateDriver(dto);
        StandardResponse response = new StandardResponse(200, "Success", null);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteDriver(@RequestParam String id){
        driverService.deleteDriver(id);
        StandardResponse response = new StandardResponse(200, "Success", null);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchDriver(@PathVariable String id){
        DriverDTO driver = driverService.searchDriver(id);
        StandardResponse response = new StandardResponse(200, "Success", driver);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllDrivers(){
        List<DriverDTO> allDrivers = driverService.getAllDrivers();
        StandardResponse response = new StandardResponse(200, "Success", allDrivers);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{stat}/available")
    public ResponseEntity getAvailableDriver(){
        DriverDTO randomDriver = driverService.getRandomDriver();
        StandardResponse response = new StandardResponse(200, "Success", randomDriver);
        return new ResponseEntity(response, HttpStatus.OK);
    }


//    private String hashPassword(String password) {
//
//        String generatedPassword = null;
//
//        try {
//
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            byte[] bytes = md.digest(password.getBytes());
//            BigInteger no = new BigInteger(1, bytes);
//            String hashtext = no.toString(16);
//            while (hashtext.length() < 32) {
//                hashtext = "0" + hashtext;
//            }
//            generatedPassword = hashtext;
//
//        }catch (NoSuchAlgorithmException ex){
//            System.out.println(ex);
//        }
//
//        return generatedPassword;
//
//    }
}
