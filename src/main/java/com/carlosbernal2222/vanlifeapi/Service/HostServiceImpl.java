package com.carlosbernal2222.vanlifeapi.Service;

import com.carlosbernal2222.vanlifeapi.Model.HostEntity;
import com.carlosbernal2222.vanlifeapi.Model.VanEntity;
import com.carlosbernal2222.vanlifeapi.Repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService{

    @Autowired
    private HostRepository hostRepository;

    @Override
    public List<HostEntity> getAllHosts() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<HostEntity> getHostById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public HostEntity createHost(HostEntity host) {
        return hostRepository.save(host);
    }

    @Override
    public void updateHost(Long id, HostEntity updatedHost) {
        Optional<HostEntity> existingHost = hostRepository.findById(id);

        existingHost.ifPresent(hostEntity -> {
            hostEntity.setName(updatedHost.getName());
            hostEntity.setEmail(updatedHost.getEmail());
            hostEntity.setPassword(updatedHost.getPassword());

            hostRepository.save(hostEntity);
        });
    }

    @Override
    public List<VanEntity> getVansByHostId(Long hostId) {
        Optional<HostEntity> host = hostRepository.findById(hostId);
        return host.map(HostEntity::getVans).orElse(Collections.emptyList());
    }

    @Override
    public void deleteHost(Long id) {
        hostRepository.deleteById(id);
    }

}
