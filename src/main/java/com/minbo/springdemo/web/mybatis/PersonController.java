//package com.minbo.springdemo.web.mybatis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class PersonController {
//
//	@Autowired
//    private PersonServices testServices;
//	
//	@RequestMapping(value = "/show")
//    public String show(){
//        return testServices.show();
//    }
//
//    @RequestMapping(value = "/person/{id}")  
//    public Object showDao(@PathVariable String id){
//        return testServices.findById(id);
//    }
//    
//}
