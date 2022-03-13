package com.mt.dbapp.services;

import com.mt.dbapp.models.ServiceModel;
import com.mt.dbapp.repositories.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    Logger logger = LoggerFactory.getLogger(ServiceService.class);

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
        // sample data that can be removed
        ServiceModel serviceModel1=new ServiceModel(null,"GDFDF","DISABLE");
        this.serviceRepository.save(serviceModel1);
        ServiceModel serviceModel2=new ServiceModel(null,"ADDFG","ENABLE");
        this.serviceRepository.save(serviceModel2);
        logger.info("Sample data created");

    }

    public List<ServiceModel> getServices(){
        return serviceRepository.findAll();
    }

    public String getServiceIDByTag(String tag){
            return String.format("{ \"id\" : \"%s\" }",serviceRepository.findServiceByTag(tag).getId().toString());
    }
}
