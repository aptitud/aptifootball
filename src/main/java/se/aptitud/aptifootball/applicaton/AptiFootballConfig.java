package se.aptitud.aptifootball.applicaton;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class AptiFootballConfig extends Configuration {
    @NotEmpty
    @JsonProperty
    private String accessKey;

    @JsonProperty
    private String url ="http://www.xmlsoccer.com/FootballDataDemo.asmx";

    @NotEmpty
    @JsonProperty
    private String databaseUrl;

    @JsonProperty
    private String collectionName="aptifootball";

    public void setUrl(String url) {
        this.url = url;
    }
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    public  String getAccessKey() {
        return accessKey;
    }
    public  String getUrl() {
        return url;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}


