package com.carlosbernal2222.vanlifeapi.Repository;

import com.carlosbernal2222.vanlifeapi.Model.VanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VanRepository extends JpaRepository<VanEntity, Long> {


}
