package com.example.product_manager.repositorty

import com.example.product_manager.entity.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, String>