
package com.mycompany.services;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vasco
 */
@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer>{
    
    List<Participant> findAllByName(String name);
    
    List<Participant> findAllByEventID(Integer eventID);
    
    Participant findByChipID(String chipID);
    
    List<Participant> findAllByEventIDAndLocationID(Integer eventID, String locationID);
    
    List<Participant> findAllByEventIDAndStarted(Integer eventID, Boolean t);
}
