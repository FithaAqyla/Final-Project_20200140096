/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uaswebservice.uasws;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aqyla-PC
 */
@RestController
@CrossOrigin
@RequestMapping("/menu")
public class CakeControllerr {
    Daftarmenu mn;
    DaftarmenuJpaController ctrl;
    
    @GetMapping
    public List<Daftarmenu> getData(){
        List<Daftarmenu> menuu = new ArrayList(){};
        
        try {
            menuu = ctrl.findDaftarmenuEntities();
        }catch(Exception e){
            menuu = List.of();
        }
        return ctrl.findDaftarmenuEntities();
    }
    
    @GetMapping("/{id}")
    public List<Daftarmenu> getDataEntities(@PathVariable("id") int id){
        List<Daftarmenu> menuu = new ArrayList(){};
        try{
            mn = ctrl.findDaftarmenu(id);
            menuu.add(mn);
        }catch(Exception e){
        menuu = List.of();//tidak berguna cuma biar tidak jomblo
        }
        return menuu;//menampilkan list data
    }
    @PostMapping
    public String insertData(HttpEntity<String> requestdata){//membuat method menambahkan data
       String message = "Menu Berhasil diTambahkan"; //pesan jika data berhasil di tambahkan
        try {
            
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper(); // membuat object baru
            
            mn = map.readValue(json_receive, Daftarmenu.class);//memasukan data ke entity
            ctrl.create(mn);// memasukan data ke db
        } catch (Exception e) {
            message = "Failed to Insert Data";//pesan ketika data gagal di tambahkan
        }
        return message; //menampilkan pesan
    }
    @PutMapping
    public String updateData(HttpEntity<String> requestdata){//membuat method edit data
       String message = "Menu Berhasil di Edit";//pesan jika data berhasil di edit
        try {
            
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();//membuat object baru
            
            mn = map.readValue(json_receive, Daftarmenu.class);//memasukan data ke entity
            ctrl.edit(mn);// memasukan data ke db
        } catch (Exception e) {
            message = "Gagal mengupdate menu";//menampilkan pesan ketika data gagal di edit
        }
        return message;//menampilkan pesan
    }
    @DeleteMapping

    public String deleteData(HttpEntity<String> requestdata){ //membuat method hapus
       String message = "Menu Berhasil diHapus"; // pesan jika data berhasil di hapus
        try {
            
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper(); // membuat object baru
            
            mn = map.readValue(json_receive, Daftarmenu.class);//memasukan data ke entity
            ctrl.destroy(mn.getId());// memasukan data ke db
        } catch (Exception e) {
            message = "Failed to delete Data"; //menampilkan pesan ketika data gagal di hapus
        }
        return message;//menampilkan pesan
    }
}
