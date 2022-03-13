package com.mt.dbapp.repositories;

import com.mt.dbapp.models.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceModel,Long> {
    public ServiceModel findServiceByTag(String tag);
}
