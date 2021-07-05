package com.saptarshi.technohrms.controller;

import com.saptarshi.technohrms.exchanges.training.AddTrainingRequest;
import com.saptarshi.technohrms.exchanges.training.AssignTrainingRequest;
import com.saptarshi.technohrms.exchanges.training.UpdateTrainingRequest;
import com.saptarshi.technohrms.service.TrainingMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "training-mgmt")
public class TrainingController {

    @Autowired
    private TrainingMgmtService trainingMgmtService;

    @RequestMapping(value = "/training", method = RequestMethod.POST)
    public ResponseEntity<?> addTraining(@RequestBody AddTrainingRequest request){
        return ResponseEntity.ok(trainingMgmtService.addTraining(request));
    }

    @RequestMapping(value = "/trainings", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTraining(){
        return ResponseEntity.ok(trainingMgmtService.getAllTraining());
    }

    @RequestMapping(value = "/training/employee", method = RequestMethod.POST)
    public ResponseEntity<?> assignTraining(@RequestBody AssignTrainingRequest request){
        return ResponseEntity.ok(trainingMgmtService.assignTraining(request));
    }

    @RequestMapping(value = "/training/employees/assigned", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAssignedTraining(){
        return ResponseEntity.ok(trainingMgmtService.getAllAssignedTraining());
    }

    @RequestMapping(value = "/training/employee/assigned", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAssignedTrainingByEmployee(@RequestParam Integer id){
        return ResponseEntity.ok(trainingMgmtService.getAllAssignedTrainingByEmployee(id));
    }

    @RequestMapping(value = "/training/employee/assigned/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAssignedTraining(@PathVariable("id") Integer id, @RequestBody UpdateTrainingRequest request){
        return ResponseEntity.ok(trainingMgmtService.updateAssignedTraining(id, request));
    }

    @RequestMapping(value = "/training/employee/assigned/training", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAssignedTrainingByTraining(@RequestParam Integer id){
        return ResponseEntity.ok(trainingMgmtService.getAllAssignedTrainingByTraining(id));
    }



}
