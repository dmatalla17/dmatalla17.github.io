

import java.util.Random;

public class Resource {

    private String resource_id;
    private String resourceName;
    private String source;
    private String resoucetype;
    private String price;
    private String provider;
    private String resourceDescription;
    private String creationDate;

    public Resource() {
    }

    Random rand = new Random(); 
    public Resource(String resourceName, String source, String resoucetype, String price, String resourceDescription) {
        
        int randnum = rand.nextInt(9000000); 
        this.resource_id = "RSC_#"+randnum;
        this.resourceName = resourceName;
        this.source = source;
        this.resoucetype = resoucetype;
        this.price = price;
        this.provider = SQLDBConnect.getCurentUser().getUserEmail();
        this.resourceDescription = resourceDescription;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResoucetype() {
        return resoucetype;
    }

    public void setResoucetype(String resoucetype) {
        this.resoucetype = resoucetype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
