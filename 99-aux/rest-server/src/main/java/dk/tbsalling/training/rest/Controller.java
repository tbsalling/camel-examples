package dk.tbsalling.training.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class Controller {

    private final Map<Integer,Vessel> vesselMap = new ConcurrentHashMap<>();

    public Controller() {
        Vessel[] vessels =  {
            new Vessel(123456789, "CHARLES V", 55.00f, 10.00f, 12.0f, 45.0f),
            new Vessel(219000178, "MHV910 RINGEN", 55.04f, 9.43f, 0.0f, 0.0f),
            new Vessel(219000184, "MHV804 ANDROMEDA", 55.71f, 10.01f, 0.0f, 0.0f),
            new Vessel(219000178, "EMMA MAERSK", 36.62f, -0.47f, 19.0f, 79.0f),
            new Vessel(123456789, "CHARLES V", 55.00f, 10.00f, 12.0f, 45.0f)
        };

        for (Vessel vessel : vessels) {
            vesselMap.put(vessel.getMmsi(), vessel);
        }
    }

    @RequestMapping("/vessels")
    public Collection<Vessel> vessels() {
        return vesselMap.values();
    }

    @RequestMapping("/vessel/{mmsi}")
    public Vessel vessel(@PathVariable(value = "mmsi") int mmsi) {
        return vesselMap.get(mmsi);
    }

}
