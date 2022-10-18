package com.inditex.hiring.repository;

import com.inditex.hiring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    public static final String queryCustom="select * from PRODUCTO p where p.BRAND_ID =:brand AND p.PART_NUMBER =:partnum";

    @Query(value=queryCustom, nativeQuery = true)
    List<Producto> searchByBrandAndPart(@Param("brand") Integer brandId, @Param("partnum") String partnumber);
}
