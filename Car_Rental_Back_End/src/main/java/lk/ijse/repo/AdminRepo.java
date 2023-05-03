package lk.ijse.repo;

import lk.ijse.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepo extends JpaRepository<Admin, String> {

    @Query(value = "SELECT adminID FROM admin ORDER BY adminID DESC LIMIT 1", nativeQuery = true)
    String getLastID();

    @Query(value = "SELECT * FROM admin WHERE userName=:userName AND password=:password",nativeQuery = true)
    Admin login(@Param("userName") String userName, @Param("password") String password);
}
