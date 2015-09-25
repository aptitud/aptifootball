package se.aptitud.aptifootball;

import com.github.pabloo99.xmlsoccer.api.service.XmlSoccerService;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;

public class XmlSoccerRepo {

    protected XmlSoccerService xmlSoccerService;


    public XmlSoccerRepo(String key, String url) {
        this.xmlSoccerService = new XmlSoccerServiceImpl();
        xmlSoccerService.setApiKey(key);
        xmlSoccerService.setServiceUrl(url);
    }
}
