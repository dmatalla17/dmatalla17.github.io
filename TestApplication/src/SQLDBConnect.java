import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SQLDBConnect {

    private static Connection con;
    private static String status;
    private static users curentUser;
    private static Statement st;

    public static void ConnectDB() {
    	
    	/**
         * 3306 is the default port for MySQL in XAMPP. Note both the 
         * MySQL server and Apache must be running. 
         */
        String url = "jdbc:mysql://localhost:3306/";

        /**
         * The MySQL user.
         */
        String user = "root";

        /**
         * Password for the above MySQL user. If no password has been 
         * set (as is the default for the root user in XAMPP's MySQL),
         * an empty string can be used.
         */
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            /**
             * Create and select a database for use. 
             */
            st.execute("CREATE DATABASE IF NOT EXISTS app2");
            st.execute("USE app2");
	    //XAMPP Connection//
            status = "Connected...";
            CreateResourceTables();
            CreateUserTables();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            status = "Not Connected";
        }

    }

    public static void CreateResourceTables() {
        String tbleOne = "Resource";

        //Check if table Resource exists
        if (!checkTable(tbleOne)) {

            try {
                String create = "CREATE TABLE Resource ("
                        + " ResourceName varchar(45) NOT NULL,"
                        + " ResourceId varchar(30) NOT NULL,"
                        + " source varchar(50) NOT NULL,"
                        + " briefDescription text NOT NULL,"
                        + " providerEmail varchar(25) NOT NULL,"
                        + " price varchar(20) NOT NULL,"
                        + "ResourceType varchar(30) NOT NULL,"
                        + "CreationDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                        + " PRIMARY KEY (ResourceId)"
                        + ") ";

                st.execute(create);

            } catch (SQLException ex) {
            	 System.out.println("" + ex);
            }
        }
    }

    public static void CreateUserTables() {
        String tble = "users";

        //Check if table Resource exists
        if (!checkTable(tble)) {

            try {
                String create = "CREATE TABLE users ("
                        + " UserName varchar(45) NOT NULL,"
                        + " password varchar(30) NOT NULL,"
                        + " UserEmail varchar(50) NOT NULL,"
                        + " CreationDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                        + " PRIMARY KEY (UserEmail)"
                        + ") ";

                
                st.execute(create);

            } catch (SQLException ex) {

                System.out.println("" + ex);
            }
        }
    }

    public static boolean checkTable(String tableName) {

        try {
            DatabaseMetaData dbm = con.getMetaData();
            // check if table exista
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {

                System.out.println("Exists");
                return true;

            } else {

                System.out.println("Does Not Exists");
                return false;
            }
        } catch (SQLException ex) {

            System.out.println(" " + ex);
        }

        return false;
    }

    public static boolean registerClient(users user) {

        boolean result = false;
        String uname = user.getUsernme();
        String userEmail = user.getUserEmail();
        String userPass = user.getUsrepass();

        if (checkUser(userEmail)) {

            System.out.println("User Exists!!");
            return false;
        }

        //Using INSERT statement to insert data into our database
        String query = "INSERT INTO users (UserName,password,userEmail) VALUES('" + uname + "','" + userPass + "','" + userEmail + "')";

        try {

            Statement st = con.createStatement();
            int value = st.executeUpdate(query);

            if (value == 1) {

                return true;
            }
        } catch (SQLException exc) {

            System.out.println(" " + exc);
        }

        return result;

    }

    /*We check if user exists first, so that we do not duplicate user details like emails*/
    public static boolean checkUser(String userEmail) {

        String query = "SELECT * FROM users WHERE userEmail = '" + userEmail + "' ";

        try {

            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            return rst.next();

        } catch (SQLException exc) {

            System.out.println(" " + exc);
        }

        return false;
    }

    public static boolean logIn(String userEmail, String pass) {

        String query = "SELECT * FROM users WHERE userEmail = '" + userEmail + "' AND password =  '" + pass + "'";

        try {

            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            curentUser = new users();
            if (rst.next()) {

                curentUser.setUserEmail(rst.getString("userEmail"));
                curentUser.setUsernme("UserName");
                curentUser.setUsrepass(rst.getString("password"));
                curentUser.setIsRegistered(true);
                return true;
            } else {

                curentUser.setIsRegistered(false);
                return false;
            }

        } catch (SQLException exc) {

            System.out.println(" " + exc);
        }

        return false;
    }

    public static ArrayList<Resource> getResources() {

        String query = "SELECT * FROM  Resource ";

        ArrayList<Resource> Resources = new ArrayList<>();
        try {

            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {

                Resource rsc = new Resource();
                rsc.setPrice(rst.getString("price"));
                rsc.setResoucetype(rst.getString("ResourceType"));
                rsc.setResourceName(rst.getString("ResourceName"));
                rsc.setResource_id(rst.getString("ResourceId"));
                rsc.setSource(rst.getString("source"));
                rsc.setProvider(rst.getString("providerEmail"));
                rsc.setResourceDescription(rst.getString("briefDescription"));
                rsc.setCreationDate(rst.getString("CreationDate"));

                Resources.add(rsc);

            }

        } catch (SQLException exc) {

            System.out.println(" " + exc);
        }

        return Resources;
    }

    public static ArrayList<Resource> getResources(String hint) {

        String query = "SELECT * FROM  Resource WHERE ResourceName LIKE '%" + hint + "%' OR source LIKE '%" + hint + "%' OR briefDescription LIKE '%" + hint + "%' "
                + " OR providerEmail LIKE '%" + hint + "%' OR  price LIKE '%" + hint + "%'  OR ResourceId LIKE '%" + hint + "%' "
                + " OR ResourceType LIKE '%" + hint + "%'";

        ArrayList<Resource> Resources = new ArrayList<>();
        try {

            Statement st = con.createStatement();
            ResultSet rst = st.executeQuery(query);

            while (rst.next()) {

                Resource rsc = new Resource();

                rsc.setPrice(rst.getString("price"));
                rsc.setResoucetype(rst.getString("ResourceType"));
                rsc.setResourceName(rst.getString("ResourceName"));
                rsc.setResource_id(rst.getString("ResourceId"));
                rsc.setSource(rst.getString("source"));
                rsc.setProvider(rst.getString("providerEmail"));
                rsc.setResourceDescription(rst.getString("briefDescription"));

                Resources.add(rsc);

            }

        } catch (SQLException exc) {

            System.out.println(" " + exc);
        }

        return Resources;
    }

    public static boolean addResource(Resource rsc) {

        String query = "INSERT INTO Resource (ResourceName,ResourceId,source,briefDescription,providerEmail,price,ResourceType)"
                + " VALUES('" + rsc.getResourceName() + "','" + rsc.getResource_id() + "','" + rsc.getSource() + "','" + rsc.getResourceDescription() + "',"
                + "'" + rsc.getProvider() + "','" + rsc.getPrice() + "','" + rsc.getResoucetype() + "')";

        try {

            Statement st = con.createStatement();
            int value = st.executeUpdate(query);

            if (value == 1) {

                return true;
            }

        } catch (SQLException exc) {

            System.out.println(" " + exc);

        }

        return false;
    }

    /*tO REMOVE A Resource, THE USER MUST BE THE ORNER OF HE Resource*/
    public static boolean removeResource(String ResourceId) {

        String query = "DELETE FROM Resource WHERE ResourceId = '" + ResourceId + "' AND providerEmail = '" + curentUser.getUserEmail() + "' ";

        try {

            Statement st = con.createStatement();
            int val = st.executeUpdate(query);
            System.out.println(" Value " + val);

            if (val >= 1) {

                return true;
            }

        } catch (SQLException exc) {

            System.out.println(" " + exc);
        }

        return false;
    }

    //This function will update current users books only
    public static boolean updateResource(String Column, String ResourceId, String newValue) {

        String query = "UPDATE Resource SET " + Column + " = '" + newValue + "' WHERE ResourceId = "
                + "'" + ResourceId + "' AND providerEmail = '" + curentUser.getUserEmail() + "'";

        try {

            Statement st = con.createStatement();
            int val = st.executeUpdate(query);
            if (val >= 1) {

                return true;
            }
        } catch (SQLException exc) {
            System.out.println(" " + exc);
        }

        return false;
    }

    public static String getStatus() {
        return status;
    }

    public static users getCurentUser() {

        return curentUser;
    }

}
