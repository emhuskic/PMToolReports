package com.pmtool.models;

import java.util.Arrays;
/**
 * Created by Emina on 21.03.2017..
 */
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(collectionResourceRel = "reports", path = "/reports")
public interface ReportDao extends JpaRepository<Report, Long> {

     List<Report> findByName(@Param("name") String name);
}
