package com.carlosbernal2222.vanlifeapi.Controller;

import com.carlosbernal2222.vanlifeapi.Model.HostEntity;
import com.carlosbernal2222.vanlifeapi.Model.VanEntity;
import com.carlosbernal2222.vanlifeapi.Service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/host")
@CrossOrigin()
public class HostController {

    @Autowired
    private HostService hostService;

    @GetMapping
    public List<HostEntity> getAllHosts() {
        return hostService.getAllHosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostEntity> getHostById(@PathVariable Long id) {
        Optional<HostEntity> host = hostService.getHostById(id);
        return host.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{hostId}/vans")
    public ResponseEntity<List<VanEntity>> getVansByHostId(@PathVariable Long hostId) {
        List<VanEntity> vans = hostService.getVansByHostId(hostId);
        return new ResponseEntity<>(vans, HttpStatus.OK);
    }

    @GetMapping("/{hostId}/vans/{vanId}")
    public ResponseEntity<VanEntity> getVanByIdFromHost(@PathVariable Long hostId, @PathVariable Long vanId) {
        Optional<HostEntity> host = hostService.getHostById(hostId);

        if (host.isPresent()) {
            List<VanEntity> vans = host.get().getVans();
            Optional<VanEntity> van = vans.stream().filter(v -> v.getId().equals(vanId)).findFirst();

            return van.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<HostEntity> createHost(@RequestBody HostEntity host) {
        HostEntity createdHost = hostService.createHost(host);
        return new ResponseEntity<>(createdHost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHost(@PathVariable Long id, @RequestBody HostEntity updatedHost) {
        hostService.updateHost(id, updatedHost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        hostService.deleteHost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
