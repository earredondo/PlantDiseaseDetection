package mx.ipn.escom.pojos;

// Imports ...

import mx.ipn.escom.classifier.Administrador;
import mx.ipn.escom.classifier.AdministradorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorAdministrador {

  /**
   * GET /create  --> Create a new user and save it in the database.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String email, String name) {
    String userId = "";
    try {
      Administrador user = new Administrador(email, name);
      userDao.save(user);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created with id = " + userId;
  }
  
  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      Administrador user = new Administrador(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user:" + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId = "";
    try {
      Administrador user = userDao.findByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
  
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      Administrador user = userDao.findOne(id);
      user.setEmail(email);
      user.setName(name);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }
  
    @RequestMapping("/saludo")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        System.out.println("Nombre: " + name);
        model.addAttribute("name", name);
        return "saludo";
    }

  // Private fields

  @Autowired
  private

 AdministradorDAO userDao;
  
}