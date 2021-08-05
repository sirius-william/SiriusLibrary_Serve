package com.sirius.dao.mysql;

import com.sirius.domain.BorrowInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface BorrowDao {
    ArrayList<BorrowInfo> selectBorrow(Integer userid);
    int insertBorrowBook(BorrowInfo info, Integer userid);
    int deleteBorrowBook(BorrowInfo info, Integer userid);
}
