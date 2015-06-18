package dk.tbsalling.training.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class Controller {

    private final Map<Integer,Vessel> vesselMap = new ConcurrentHashMap<>();

    {
        Vessel[] vessels =  {
            new Vessel(123456789, "CHARLES V", 55.00f, 10.00f, 12.0f, 45.0f),
            new Vessel(219000178, "MHV910 RINGEN", 55.04f, 9.43f, 0.0f, 0.0f),
            new Vessel(219000184, "MHV804 ANDROMEDA", 55.71f, 10.01f, 0.0f, 0.0f),
            new Vessel(220417000, "EMMA MAERSK", 36.62f, -0.47f, 19.0f, 79.0f),
            new Vessel(211551430, "COCO", 55.00f, 10.00f, 12.0f, 45.0f)
        };

        for (Vessel vessel : vessels) {
            vesselMap.put(vessel.getMmsi(), vessel);
        }
    }

    /**
     * GET /vessels HTTP/1.1
     * Host: localhost:8080
     * Cache-Control: no-cache
     */
    @RequestMapping("/vessels")
    public Collection<Vessel> vessels() {
        return vesselMap.values();
    }

    /**
     * GET /vessel/219002224 HTTP/1.1
     * Host: localhost:8080
     * Cache-Control: no-cache
     */
    @RequestMapping(value = "/vessel/{mmsi}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vessel vessel(@PathVariable(value = "mmsi") int mmsi) {
        return vesselMap.get(mmsi);
    }

    /**
     * POST /vessel HTTP/1.1
     * Host: localhost:8080
     * Content-Type: application/json
     * Cache-Control: no-cache
     *
     * { "mmsi": 219002224, "name": "TEST", "latitude": 55.71, "longitude": 10.01, "speed": 0, "course": 0 }
     */
    @RequestMapping(name = "/vessel/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addVessel(@RequestBody Vessel vessel) {
        vesselMap.put(vessel.getMmsi(), vessel);
    }

}
