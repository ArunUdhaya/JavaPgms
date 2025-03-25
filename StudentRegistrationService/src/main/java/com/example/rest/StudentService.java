/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/students")
public class StudentService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Student student) {
        // You can add code here to save the student to a database or perform other logic
        
        String result = "Student registered: " + student.getFirstName() + " " + student.getLastName();
        return Response.status(201).entity(result).build();
    }
}
