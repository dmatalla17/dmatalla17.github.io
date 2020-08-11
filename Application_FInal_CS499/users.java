

public class users {
    
    private String usernme;
    private String usrepass;
    private String userEmail;
    private boolean isRegistered;

    public users() {
        
        isRegistered = false;
        
    }

    public boolean isIsRegistered() {
        
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
    
    

    public users(String usernme, String usrepass, String userEmail) {
        
        
        this.usernme = usernme;
        this.usrepass = usrepass;
        this.userEmail = userEmail;
    }
    
    

    public String getUsernme() {
        return usernme;
    }

    public void setUsernme(String usernme) {
        this.usernme = usernme;
    }

    public String getUsrepass() {
        return usrepass;
    }

    public void setUsrepass(String usrepass) {
        this.usrepass = usrepass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    
    
    
}
