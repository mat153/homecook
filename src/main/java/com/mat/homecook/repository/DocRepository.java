package com.mat.homecook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.mat.homecook.model.Doc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DocRepository  extends JpaRepository<Doc,Integer>{

    @Query(value = "select * from docs where doc_name like %:keyword%",nativeQuery = true)
    List<Doc> findByKeyword(@Param("keyword") String keyword);

}
