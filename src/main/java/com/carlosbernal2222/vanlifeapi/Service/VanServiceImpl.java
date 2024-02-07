package com.carlosbernal2222.vanlifeapi.Service;

import com.carlosbernal2222.vanlifeapi.Model.VanEntity;
import com.carlosbernal2222.vanlifeapi.Repository.VanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VanServiceImpl implements VanService{

    @Autowired
    private VanRepository vanRepository;


    @Override
    public List<VanEntity> getAllVans() {
        return vanRepository.findAll();
    }

    @Override
    public Optional<VanEntity> getVanById(Long id) {
        return vanRepository.findById(id);
    }

    @Override
    public VanEntity createVan(VanEntity van) {
        return vanRepository.save(van);
    }

    @Override
    public  void updateVan(Long id, VanEntity updatedVan){
        Optional<VanEntity> existingVan = vanRepository.findById(id);

        existingVan.ifPresent(vanEntity -> {
            vanEntity.setName(updatedVan.getName());
            vanEntity.setPrice(updatedVan.getPrice());
            vanEntity.setDescription(updatedVan.getDescription());
            vanEntity.setImgUrl(updatedVan.getImgUrl());
            vanEntity.setType(updatedVan.getType());

            vanRepository.save(vanEntity);
        });
    }

    @Override
    public void deleteVan(Long id){
        vanRepository.deleteById(id);
    }
}
