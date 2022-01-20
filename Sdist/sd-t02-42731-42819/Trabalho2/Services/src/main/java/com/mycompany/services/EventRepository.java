
package com.mycompany.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vasco
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
    
    List<Event> findAllByEventName(String name);
    
    List<Event> findAllByEventDate(String date);
    
    Event findByEventName(String name);
    
    Event findByEventID(Integer eventID);
}
