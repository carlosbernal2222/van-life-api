package com.carlosbernal2222.vanlifeapi.Controller;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.carlosbernal2222.vanlifeapi.Model.HostEntity;
import com.carlosbernal2222.vanlifeapi.Model.VanEntity;
import com.carlosbernal2222.vanlifeapi.Repository.HostRepository;
import com.carlosbernal2222.vanlifeapi.Service.VanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vans")
@CrossOrigin()
public class VanController {

    @Autowired
    private VanService vanService;

    @Autowired
    private HostRepository hostRepository;

    @GetMapping
    public List<VanEntity> getAllVans(){
        return vanService.getAllVans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VanEntity> getVanById(@PathVariable Long id) {
        return vanService.getVanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addVan")
    public ResponseEntity<VanEntity> createVan(@RequestBody VanEntity van) {
        // Assuming you have hostId in the request body, replace it with the actual field name
        Long hostId = van.getHost().getId();

        Optional<HostEntity> optionalHost = hostRepository.findById(hostId);
        if (optionalHost.isPresent()) {
            HostEntity host = optionalHost.get();
            host.addVan(van); // This will associate the van with the host and add it to the list

            hostRepository.save(host); // Save the host to persist the changes

            VanEntity savedVan = vanService.createVan(van); // Save the van separately if needed

            return ResponseEntity.ok(savedVan);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVan(@PathVariable Long id, @RequestBody VanEntity updatedVan){
        Optional<VanEntity> existingVan = vanService.getVanById(id);

        if(existingVan.isPresent()){
            vanService.updateVan(id, updatedVan);
            return new ResponseEntity<>("Van updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Van not Found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVan(@PathVariable Long id){
        Optional<VanEntity> existingVan = vanService.getVanById(id);

        if(existingVan.isPresent()){
            vanService.deleteVan(id);
            return new ResponseEntity<>("Van deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Van not Found", HttpStatus.NOT_FOUND);
        }
    }

}
