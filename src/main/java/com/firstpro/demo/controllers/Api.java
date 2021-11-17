package com.firstpro.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstpro.demo.Rebo.loginRebo;
import com.firstpro.demo.model.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*" )
@RequestMapping("api")
public class Api {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private loginRebo loginRebo;
    private boolean isActive;


    @PostMapping (path = "/add")
    public @ResponseBody String addNewUser (String UserName, String password, boolean isActive, boolean isAdmin){

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            password = bytesToHex(encodedhash);
        }catch (Exception e){

        }

        login n = new login();
        n.setUserName(UserName);
        n.setPassword(password);
        n.setIsActive(isActive);
        n.setIsAdmin(isAdmin);
         loginRebo.save(n);
        return "user Saved";

    }
   /* @Transactional
    @PutMapping("/update")
    public String updateUserInfo(int id, String UserName, Optional<Boolean> isActive, Optional<Boolean> isAdmin) {
           Optional<login> l = loginRebo.findById(id);
           if(l.isPresent()){
               l.get().setIsAdmin(isAdmin.isEmpty()? false: isAdmin.get());
               l.get().setUserName(UserName);
               l.get().setIsActive(isActive.isEmpty()? false : isActive.get());
               loginRebo.save(l.get()); 
               return "Record updated successfully";
           }else{
               return "not found";
           }

          }

    @DeleteMapping  (path = "/deleteUser")
    public String deleteRole(int Id) {
        loginRebo.deleteById(Id);
        return "user was deleted with id:"+ Id;
    }*/
    @GetMapping(path="/all")
    public @ResponseBody Iterable<login> getAllUsers() {
        // This returns a JSON or XML with the users
        return loginRebo.findAll();
    }
   /* @GetMapping("/users")
    public JsonNode getUsers(@RequestParam(required = false) String filter, int page, int pagesize) {

        if (filter == null || filter.equals("null")) {
            filter = "";
        }

        Pageable pageable = PageRequest.of(page - 1, pagesize);
        var users = loginRebo.findForPagination(filter, pageable);
        var total = loginRebo.findAllUsers(filter);
        ObjectNode res = objectMapper.createObjectNode();

        res.putPOJO("items", users);

        ObjectNode pagination = res.putPOJO("pagination", new Pagination(total, users.size(), pagesize, page, Math.ceil(total / pagesize)));

        return res;
    }*/


    @PostMapping("/admin")
    public String getMyName(String UserName, String password, HttpSession session, boolean isAdmin, boolean isActive) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            Optional<login> n = loginRebo.findByUser(UserName, bytesToHex(encodedhash));
            if ( (!n.isEmpty() ) &&  (n.get().getIsAdmin() == true) ){
                session.setAttribute("isActive", n.get().getIsActive());
                session.setAttribute("isAdmin", n.get().getIsAdmin());
                session.setAttribute("id", n.get().getId());
                session.setAttribute("username", n.get().getUserName());

                return "Admin" + n.get().getIsActive() + n.get().getIsAdmin();
            }
            else if (!n.isEmpty() &&  n.get().getIsAdmin() == false){
                return "user" + n.get().getIsActive() + n.get().getIsAdmin();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "false";

    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
   /* @PostMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login.vue";
    }

    @Transactional
    @PutMapping("/reset")
    public String resetPassword(int id, String UserName, String password, Optional<Boolean> isActive, Optional<Boolean> isAdmin) {

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            password = bytesToHex(encodedhash);
        }catch (Exception e){

        }

        Optional<login> l = loginRebo.findById(id);
        if(l.isPresent()){
            l.get().setIsAdmin(isAdmin.isEmpty()? false: isAdmin.get());
            l.get().setUserName(UserName);
            l.get().setPassword(password);
            l.get().setIsActive(isActive.isEmpty()? false : isActive.get());
            loginRebo.save(l.get());
            return "password updated successfully";
        }else{
            return "not found";

    }*/
}

