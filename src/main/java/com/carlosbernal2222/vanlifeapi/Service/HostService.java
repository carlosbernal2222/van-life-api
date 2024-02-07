package com.carlosbernal2222.vanlifeapi.Service;

import com.carlosbernal2222.vanlifeapi.Model.HostEntity;
import com.carlosbernal2222.vanlifeapi.Model.VanEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface HostService {

    List<HostEntity> getAllHosts();
    Optional<HostEntity> getHostById(Long id);
    HostEntity createHost(HostEntity host);
    void updateHost(Long id, HostEntity updatedVan);
    List<VanEntity> getVansByHostId(Long hostId);

    void deleteHost(Long id);

}
