package com.mt.dbapp.controllers;

import com.mt.dbapp.models.ServiceModel;
import com.mt.dbapp.repositories.ServiceRepository;
import com.mt.dbapp.services.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {
    private final ServiceService serviceService;
    Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    @GetMapping("/services")
    public List<ServiceModel> getServices() {
        logger.info("[GET] /services endpoint is called !");
        return serviceService.getServices();
    }

    @GetMapping("/services/{tag}")
    public String getServiceByTag(@PathVariable("tag") String tag) {
        logger.info("[GET] /services/"+tag +" endpoint is called  with tag: " );
        return serviceService.getServiceIDByTag(tag);
    }

}
