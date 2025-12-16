package group7_koshpal;

import view.signup;
import controller.signupController;

/**

 *

 * @author User

 */

public class Group7_KoshPal {



    /**

     * @param args the command line arguments

     */

    public static void main(String[] args) {
        signup signup = new signup();
        signupController uc = new signupController(signup);
        uc.open();
   } 
    
}
