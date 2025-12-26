package group7_koshpal;

import view.dashboard;
import controller.dashcontroller;

/**

 *

 * @author User

 */

public class Group7_KoshPal {



    /**

     * @param args the command line arguments

     */

    public static void main(String[] args) {
        dashboard dashboard = new dashboard();
        dashcontroller uc = new dashcontroller(dashboard);
        uc.open();
   } 
    
}
