package com.carlosbernal2222.vanlifeapi.Service;

import com.carlosbernal2222.vanlifeapi.Model.VanEntity;

import java.util.List;
import java.util.Optional;

public interface VanService {


    List<VanEntity> getAllVans();
    Optional<VanEntity> getVanById(Long id);
    VanEntity createVan(VanEntity van);
    void updateVan(Long id, VanEntity updatedVan);
    void deleteVan(Long id);


}
