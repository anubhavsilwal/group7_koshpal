package controller;

import view.dashboard;
import dao.userDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.userModel;
import view.signup;

/**
 *
 * @author anubhavsilwal
 */
public class dashcontroller {
    private final userDao userdao =  new userDao();
    private final dashboard userView;
    
    
    public dashcontroller(dashboard userView){
        this.userView = userView;
        
        userView.AddUserListner(new SignUpListener());
        userView.LoginButtonListener(new LoginListener());
    }
    
   
    public void open(){
        this.userView.setVisible(true);
        
    }

    public void close(){
        this.userView.dispose();
    }
    
    class LoginListener implements ActionListener {
@Override
        public void actionPerformed(ActionEvent ex) {
            login log = new login();
            loginController lc = new loginController(log);
            lc.open();
        }
    }
    
    
    
     class SignUpListener implements ActionListener {
@Override
        public void actionPerformed(ActionEvent e) {
            try{
                String username = userView.getUsernameField().getText();
                String email = userView.getEmailField().getText();
                String password = userView.getPasswordField().getText();
                userModel usermodel = new userModel (username,email,password);
                boolean check = userdao.check(usermodel);
                if(check){
                    JOptionPane.showMessageDialog(userView, "Duplicated");
                }else{
               userdao.signup(usermodel);
               JOptionPane.showMessageDialog(userView, "Successful");
            }
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
}
