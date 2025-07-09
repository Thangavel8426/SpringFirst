package com.example.cms.repository;

import com.example.cms.model.CmsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsRepo extends JpaRepository<CmsModel, Integer> {
}
