/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Point[] pts2 =new Point[]{new Point(110, 130),new Point(125, 120)};
        Point[] pts3 =new Point[]{new Point(110, 220),new Point(135, 130)};
        Blueprint bp=new Blueprint("autor_1", "bps_1",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        Blueprint bp2 =new Blueprint("autor_2", "bps_2",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        Blueprint bp3 =new Blueprint("autor_3", "bps_3",pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
    }
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> bpba = new HashSet<>();
        Set<Tuple<String, String>> keys = blueprints.keySet();
        for(Tuple<String, String> i : keys){
            if(i.getElem1().equals(author)){
                bpba.add(blueprints.get(i));
            }
        }
        return bpba;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> bpba = new HashSet<>();
        Set<Tuple<String, String>> keys = blueprints.keySet();
        for(Tuple<String, String> i : keys){
                bpba.add(blueprints.get(i));
        }
        return bpba;
    }

    @Override
    public void updatePoints(String author, String bpname, List<Point> points) {
        Blueprint b = blueprints.get(new Tuple<>(author, bpname));
        b.addPoint(points.get(0));
    }

    @Override
    public void deleteBlueprint(String author, String bpname) {
        blueprints.remove(new Tuple<>(author, bpname));
    }


}
