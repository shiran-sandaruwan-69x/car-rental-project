package lk.ijse.service;

import lk.ijse.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    void saveAdmin(AdminDTO dto);
    void updateAdmin(AdminDTO dto);
    AdminDTO searchAdmin(String id);
    void deleteAdmin(String id);
    List<AdminDTO> getAllAdmin();
    String getLastLoginID();
    AdminDTO login(String userName, String password);
}
