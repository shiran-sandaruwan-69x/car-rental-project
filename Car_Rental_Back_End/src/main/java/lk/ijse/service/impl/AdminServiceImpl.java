package lk.ijse.service.impl;

import lk.ijse.dto.AdminDTO;
import lk.ijse.entity.Admin;
import lk.ijse.repo.AdminRepo;
import lk.ijse.service.AdminService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public void saveAdmin(AdminDTO dto) {
        Admin admin = modelMapper.map(dto, Admin.class);
        admin.setAdminID(getLastLoginID());
        adminRepo.save(admin);
    }

    @Override
    public void updateAdmin(AdminDTO dto) {
        if (adminRepo.existsById(dto.getAdminID())){
            adminRepo.save(modelMapper.map(dto,Admin.class));
        }else{
            throw new RuntimeException("No such admin for update..!");
        }
    }

    @Override
    public AdminDTO searchAdmin(String id) {
        Optional<Admin> admin = adminRepo.findById(id);
        if(admin.isPresent()){
            return modelMapper.map(admin,AdminDTO.class);
        }else{
            throw new RuntimeException("No admin for id: " + id);
        }
    }

    @Override
    public void deleteAdmin(String id) {
        if (adminRepo.existsById(id)){
            adminRepo.deleteById(id);
        }else{
            throw new RuntimeException("No admin for delete id: " + id);
        }
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        List<Admin> admins = adminRepo.findAll();
        return modelMapper.map(admins,new TypeToken<List<AdminDTO>>(){}.getType());
    }

    @Override
    public String getLastLoginID() {
        String lastID = adminRepo.getLastID();
        if (lastID != null) {
            String[] split = lastID.split("A");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id < 10) return "A00" + id;
            else if (id < 100) return "A0" + id;
            else return "A" + id;
        }else{
            return "A001";
        }
    }

    @Override
    public AdminDTO login(String userName, String password) {
        Admin login = adminRepo.login(userName, password);
        if (login == null){
            return null;
        }
        return modelMapper.map(login,AdminDTO.class);
    }

}
