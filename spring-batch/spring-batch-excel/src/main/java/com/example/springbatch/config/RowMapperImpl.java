package com.example.springbatch.config;

import com.example.springbatch.model.Customers;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

public class RowMapperImpl implements RowMapper<Customers> {
    public RowMapperImpl() {
    }

    @Override
    public Customers mapRow(RowSet rs) throws Exception {
        if (rs == null || rs.getCurrentRow() == null) {
            return null;
        }
        Customers bl = new Customers();
        bl.setId(Integer.parseInt(rs.getCurrentRow()[0]));
        System.out.println(bl.getId());
        return bl;
    }
}
