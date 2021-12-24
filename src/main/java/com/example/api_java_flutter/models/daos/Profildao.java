package com.example.api_java_flutter.models.daos;

import com.example.api_java_flutter.models.Profilbean;
import com.example.api_java_flutter.models.Userbean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Profildao extends JpaRepository<Profilbean, Long> {
    Profilbean findByUser(Userbean user);
    Profilbean findByIdProfil(int id);


//    @Transactional
//    @Modifying
//    @Query("update Profilbean p set p.name = :name , p.firstName = :firstName, p.age = :age where p.idProfil = :idProfil")
//    void update(int idProfil, String name, String firstName, int age);
}
