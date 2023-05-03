package lk.ijse.Controller;

import lk.ijse.dto.AdminDTO;
import lk.ijse.service.AdminService;
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
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addAdmin(@RequestBody AdminDTO dto){
//        dto.setPassword(hashPassword(dto.getPassword()));
        adminService.saveAdmin(dto);
        StandardResponse response = new StandardResponse(200, "Success", dto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteAdmin(@RequestParam String id){
        adminService.deleteAdmin(id);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateAdmin(@RequestBody AdminDTO dto){
        dto.setPassword(dto.getPassword());
        adminService.updateAdmin(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", dto), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchAdmin(@PathVariable String id){
        AdminDTO adminDto = adminService.searchAdmin(id);
        return new ResponseEntity(new StandardResponse(200, "Success", adminDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<AdminDTO> allAdmin = adminService.getAllAdmin();
        return new ResponseEntity(new StandardResponse(200, "Success", allAdmin), HttpStatus.OK);
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
