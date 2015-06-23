package dk.tbsalling.training.camel;

//import org.apache.camel.Exchange;
//import org.apache.camel.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// No Camel dependencies are necessary in this file!

@Component("heartBeatBean")
public class HeartBeatBean {

    private final static Logger LOG = LoggerFactory.getLogger(HeartBeatBean.class);

    public HeartBeatBean() {
        LOG.info("HeartBeatBean created.");
    }

    //@Handler
    //public void tick(Exchange exchange) {
    //    LOG.info("Tick!");
    //}

    public void tock() {
        LOG.info("Tock!");
    }

}
